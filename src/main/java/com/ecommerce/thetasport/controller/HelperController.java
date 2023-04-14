package com.ecommerce.thetasport.controller;

import com.ecommerce.thetasport.dao.OrderDAO;
import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.dao.UserDAO;
import com.ecommerce.thetasport.model.UserBean;
import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitorImpl;
import com.ecommerce.thetasport.service.productabstractfactory.Director;
import com.ecommerce.thetasport.service.productabstractfactory.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * HelperController è una classe di utilità che fornisce metodi statici per facilitare <br>
 * il lavoro di altri controller.
 *
 * @author Theta Sport
 * @version 1.0
 */
public class HelperController {

    /**
     * Verifica se l'utente ha effettuato il login e ha un carrello della spesa, <br>
     * se non ha una sessione attiva si genera una nuova sessione.
     *
     * @param request la richiesta HTTP inviata dal client
     */
    public static void verifyLoginAndCart( @NotNull HttpServletRequest request ) {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            nullSession( request );
        }
    }

    /**
     * Popola l'attributo "productBeanList" della richiesta con la lista di prodotti
     * disponibili nel database.
     *
     * @param request la richiesta HTTP inviata dal client
     */
    public static void ForwardProductList( @NotNull HttpServletRequest request ) {
        try {
            request.setAttribute( "productBeanList", ProductDAO.getProductBeanList() );
        } catch ( SQLException e ) {
            throw new RuntimeException( "Error in ForwardProductList SQLException" + e );
        }
    }

    /**
     * Se non esiste una sessione attiva, ne viene creata una e vengono inizializzati
     * i campi per il login e il carrello della spesa.
     *
     * @param request la richiesta HTTP inviata dal client
     */
    public static void nullSession( @NotNull HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Cart myCart = new Cart();
        // variables are set for the session
        session.setAttribute( "isLogged", 0 );
        // variables are set for the session
        session.setAttribute( "login", 0 );
        session.setAttribute( "itemsCart", myCart );
        session.setAttribute( "numItemCart",  myCart.sizeCart() );
        // variables are set for the request
        request.setAttribute( "login", 0);
    }

    /**
     * Gestisce l'errore di login e mostra un messaggio di errore all'utente.
     *
     * @param request  la richiesta HTTP inviata dal client
     * @param response la risposta HTTP inviata dal server
     * @param message  il messaggio di errore da visualizzare
     * @throws ServletException se si verificano problemi nella gestione della richiesta HTTP
     * @throws IOException      se si verificano problemi nella gestione dell'input/output
     */
    public static void loggedError( @NotNull HttpServletRequest request, HttpServletResponse response, String message ) throws ServletException, IOException {
        request.setAttribute( "errorMessage", message );
        request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
    }

    /**
     * loggedOrRegistrationSuccessful viene invocato se il login o la registrazione hanno avuto successo.<br>
     * allora viene recuperata la sessione corrente o, se esiste, viene invalidata.<br>
     * Quindi viene creata una nuova sessione impostando il tempo massimo (in questo caso 30 minuti), per poi recuperare<br>
     * i dati inerenti all'utente/admin appena registrato, si imposta il campo di login della sessione e della landing page,<br>
     * e infine si reindirizza alla landing page che è condizionata dal tipo di risultato ottenuto dal login.<br>
     *
     * @param request Richiesta effettuata tramite browser
     * @param response Risposta
     * @param email mail dall'utente registrato
     * @param isLogged stato del log: 1 dall'utente, 2 dall'amministratore
     * @param landingPage la rispettiva jsp target
     * @throws ServletException Definisce un'eccezione generale che una servlet può generare quando incontra delle difficoltà.
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    public static void loggedOrRegistrationSuccessful( @NotNull HttpServletRequest request, HttpServletResponse response, String email, int isLogged, String landingPage ) throws ServletException, IOException, SQLException, ClassNotFoundException {
        // the current session is retrieved and if it exists it is invalidated
        HttpSession oldSession = request.getSession( false );
        if ( oldSession != null ) {
            oldSession.invalidate(); //  the session is invalidated
        }
        // the new current session is taken
        HttpSession currentSession = request.getSession();
        currentSession.setMaxInactiveInterval( 30 * 60 ); // the new session will last 30 minutes
        UserBean userBean = new UserDAO().getUser( email );
        currentSession.setAttribute( "userBean", userBean );
        currentSession.setAttribute( "isLogged", isLogged );
        currentSession.setAttribute( "login", isLogged );
        request.setAttribute( "login", isLogged );
        Cart myCart = new Cart();
        currentSession.setAttribute( "itemsCart", myCart );
        currentSession.setAttribute( "numItemCart", myCart.sizeCart() );
        if ( landingPage.equals("jsp/index.jsp") ) {
            // home page
            HelperController.ForwardProductList( request );
        } else {
            // admin page
            request.setAttribute( "addproduct", false );
            request.setAttribute( "editproduct", false );
            request.setAttribute( "editsingleproduct", false );
            request.setAttribute( "earningMonthly", OrderDAO.getSumPriceOrderMonthly() );
            request.setAttribute( "earningYears", OrderDAO.getSumPriceOrderYear() );
            request.setAttribute( "earningTotal", OrderDAO.getSumPriceOrderTotal() );
            request.setAttribute( "orderList", OrderDAO.getSumPriceOrderTotal() );
        }
        request.getRequestDispatcher( landingPage ).forward( request, response );
    }

    /**
     * Prende il codice di un prodotto dalla richiesta HTTP, crea un oggetto prodotto utilizzando il metodo "createProduct" <br>
     * della classe "Director" e lo salva nell'attributo "singleProduct" della richiesta. <br>
     * Infine, inoltra la richiesta alla pagina JSP "single_product.jsp".
     *
     * @param request  l'oggetto HttpServletRequest
     * @param response l'oggetto HttpServletResponse
     * @throws ServletException se si verifica un errore di servlet
     * @throws IOException se si verifica un errore di I/O
     */
    public static void singleProduct(@NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
        Product product;
        try {
            product = Director.createProduct( ProductDAO.getSingleProduct( code ) );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Exception in HelperController/getCode" + e );
        }
        HelperController.verifyLoginAndCart( request );
        request.setAttribute( "singleProduct", product );
        request.getRequestDispatcher( "jsp/single_product.jsp" ).forward( request, response );
    }

    /**
     * Verifica che l'utente sia loggato prima di accedere alla pagina del carrello. <br>
     * Se l'utente non è loggato, inoltra la richiesta alla pagina JSP "login.jsp".
     *
     * @param request  l'oggetto HttpServletRequest
     * @param response l'oggetto HttpServletResponse
     * @throws ServletException se si verifica un errore di servlet
     * @throws IOException se si verifica un errore di I/O
     */
    public static void statusLog( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession( false );
        // variables are retrieved from the session
        int isLogged = ( int ) session.getAttribute( "isLogged" );
        if ( isLogged == 0 ) {
            request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
        }
    }

    /**
     * Aggiunge un prodotto al carrello dell'utente e aggiorna l'attributo "itemsCart" della sessione con il nuovo carrello. <br>
     * Se la sessione è nulla, inoltra la richiesta alla pagina JSP "null_session.jsp". <br>
     * Se l'utente non è loggato, inoltra la richiesta alla pagina JSP "login.jsp".
     *
     * @param request  l'oggetto HttpServletRequest
     * @param response l'oggetto HttpServletResponse
     * @throws ServletException se si verifica un errore di servlet
     * @throws IOException se si verifica un errore di I/O
     */
    public static void addCart( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            nullSession( request );
        } else {
            statusLog( request,response );
            Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
            int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
            try {
                myCart.add( Director.createProduct( ProductDAO.getSingleProduct( code ) ) );
            } catch ( SQLException e ) {
                throw new RuntimeException( "SQL Exception in HelperController/addCart" + e );
            }
            session.setAttribute( "itemsCart", myCart );
            session.setAttribute( "numItemCart", myCart.sizeCart() );
        }
    }

    /**
     * Rimuove un prodotto dal carrello dell'utente e aggiorna l'attributo "itemsCart" della sessione con il nuovo carrello. <br>
     * Se la sessione è nulla, inoltra la richiesta alla pagina JSP "null_session.jsp". <br>
     * Se l'utente non è loggato, inoltra la richiesta alla pagina JSP "login.jsp".
     *
     * @param request  l'oggetto HttpServletRequest
     * @param response l'oggetto HttpServletResponse
     * @throws ServletException se si verifica un errore di servlet
     * @throws IOException se si verifica un errore di I/O
     * @throws SQLException se si verifica un errore di SQL
     */
    public static void removeCart( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            nullSession( request );
            request.setAttribute( "errorMessageCart", "do login" );
        } else {
            statusLog( request, response );
            Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
            int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
            myCart.decreaseQuantity( Director.createProduct( ProductDAO.getSingleProduct( code ) ) );
            session.setAttribute( "itemsCart", myCart );
            session.setAttribute( "numItemCart", myCart.sizeCart() );
        }
    }

    /**
     * Metodo che gestisce la pagina del carrello.
     *
     * @param request  La richiesta HTTP dal client.
     * @param response La risposta HTTP dal server.
     *
     * @throws ServletException Se si verifica un errore nel servlet.
     * @throws IOException      Se si verifica un errore di input/output.
     */
    public static void cartPage( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            request.setAttribute( "errorCart", true );
            request.setAttribute( "errorMessage", "Error" );
            request.getRequestDispatcher( "jsp/cart.jsp" ).forward( request, response );
        } else {
            statusLog( request,response );
            Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
            ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
            double total = shoppingCartVisitor.getTotal();
            session.setAttribute( "itemsCart", myCart );
            request.setAttribute("totalPrice", total );
            request.getRequestDispatcher( "jsp/cart.jsp" ).forward( request, response );
        }
    }

    /**
     * Metodo che aggiunge un prodotto al carrello dalla pagina dell'indice o dalla pagina di un singolo prodotto.
     *
     * @param request  La richiesta HTTP dal client.
     * @param response La risposta HTTP dal server.
     *
     * @throws ServletException Se si verifica un errore nel servlet.
     * @throws IOException      Se si verifica un errore di input/output.
     */
    public static void addCartCaseIndexOrSingleProduct(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        addCart( request,response );
    }

    /**
     * Metodo che aggiunge un prodotto al carrello dalla pagina del carrello.
     *
     * @param request  La richiesta HTTP dal client.
     * @param response La risposta HTTP dal server.
     *
     * @throws ServletException Se si verifica un errore nel servlet.
     * @throws IOException      Se si verifica un errore di input/output.
     */
    public static void addCartCaseCart( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        addCart( request, response );
        cartPage( request, response );
    }

    /**
     * Metodo che rimuove un prodotto dal carrello dalla pagina dell'indice o dalla pagina di un singolo prodotto.
     *
     * @param request  La richiesta HTTP dal client.
     * @param response La risposta HTTP dal server.
     *
     * @throws ServletException Se si verifica un errore nel servlet.
     * @throws IOException      Se si verifica un errore di input/output.
     * @throws SQLException     Se si verifica un errore durante l'accesso al database.
     * @throws ClassNotFoundException Se non si trova la classe specificata.
     */
    public static void removeCartCaseIndexOrSingleProduct(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException, ClassNotFoundException {
        removeCart( request, response );
    }

    /**
     * Rimuove gli articoli dal carrello e mostra la pagina del carrello.
     *
     * @param request la richiesta HTTP in arrivo
     * @param response la risposta HTTP in uscita
     * @throws ServletException se si verifica un errore durante il processo di servlet
     * @throws IOException se si verifica un errore di I/O durante il processo di servlet
     * @throws SQLException se si verifica un errore SQL durante il processo di servlet
     * @throws ClassNotFoundException se non viene trovata la classe durante il processo di servlet
     */
    public static void removeCartCaseCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        removeCart(request,response);
        cartPage(request,response);
    }

    /**
     * Rimuove un oggetto dal carrello dell'utente.
     *
     * @param request La richiesta HTTP in ingresso.
     * @param response La risposta HTTP in uscita.
     * @throws ServletException Se si verifica un errore durante l'esecuzione del servlet.
     * @throws IOException Se si verifica un errore durante l'input/output.
     * @throws SQLException Se si verifica un errore durante l'accesso al database.
     * @throws ClassNotFoundException Se non è possibile trovare la classe specificata.
     */
    public static void removeObjectCart( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            nullSession( request );
            request.setAttribute( "errorMessageCart", "do login" );
        } else {
            statusLog( request, response );
            Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
            int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
            myCart.remove( Director.createProduct( ProductDAO.getSingleProduct( code ) ) );
            session.setAttribute( "itemsCart", myCart );
            session.setAttribute( "numItemCart", myCart.sizeCart() );
        }
        cartPage( request, response );
    }

    /**
     * Carica la pagina del checkout.
     *
     * @param request La richiesta HTTP in ingresso.
     */
    public static void checkoutPage( @NotNull HttpServletRequest request ) {
        HttpSession session = request.getSession( false );
        // recupero variabili dalla sessione
        int isLogged = ( int ) session.getAttribute( "isLogged" );
        Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
        // setto variabili per la request
        session.setAttribute( "login", isLogged );
        session.setAttribute( "itemsCart", myCart );
        session.setAttribute( "numItemCart", myCart.sizeCart() );
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
        double total = shoppingCartVisitor.getTotal();
        request.setAttribute( "totalPrice", total );
    }

    /**
     * Imposta le variabili necessarie per la pagina di ringraziamento dopo l'acquisto e svuota il carrello.
     *
     * @param request La richiesta HTTP in ingresso.
     * @param session La sessione HTTP.
     * @param myCart Il carrello dell'utente.
     * @param country Il paese dell'utente.
     * @param firstName Il nome dell'utente.
     * @param lastName Il cognome dell'utente.
     * @param address L'indirizzo dell'utente.
     * @param shippingAddress L'indirizzo di spedizione dell'utente.
     * @param stateCountry Lo stato/provincia dell'utente.
     * @param postalCode Il codice postale dell'utente.
     * @param email L'email dell'utente.
     * @param phone Il numero di telefono dell'utente.
     * @param orderNotes Eventuali note sull'ordine dell'utente.
     */
    public static void setVarThankYouPage( @NotNull HttpServletRequest request, HttpSession session, Cart myCart,
                                           String country, String firstName, String lastName, String address,
                                           String shippingAddress, String stateCountry, String postalCode, String email,
                                           String phone, String orderNotes ) {
        request.setAttribute( "country", country );
        request.setAttribute( "name", firstName + " " + lastName );
        request.setAttribute( "address", address );
        request.setAttribute( "shippingAddress", shippingAddress );
        request.setAttribute( "stateCountry", stateCountry );
        request.setAttribute( "postalCode", postalCode );
        request.setAttribute( "email", email );
        request.setAttribute( "phone", phone );
        request.setAttribute( "orderNotes", orderNotes );
        myCart.removeAll();
        session.setAttribute( "itemsCart", myCart );
        session.setAttribute( "numItemCart", myCart.sizeCart() );
    }
}