package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.OrderDAO;
import com.ecommerce.thetasport.service.productabstractfactory.ManagerProduct;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

/**
 * HelperControllerAdmin è una classe di utilità che fornisce metodi statici per facilitare <br>
 * il lavoro di altri controller.
 *
 * @author Theta Sport
 * @version 1.0
 */
public class HelperControllerAdmin {


    /**
     * Metodo che imposta le varie pagine dell'area amministrativa in base alle operazioni effettuate <br>
     * dall'utente, senza l'utilizzo di una struttura dati collection per evitare errori nel passaggio
     * dei dati.
     *
     * @param request Oggetto HttpServletRequest che rappresenta la richiesta HTTP.
     * @param addProduct Flag booleano che indica se l'utente ha effettuato un'operazione di aggiunta prodotto.
     * @param productCreated Flag booleano che indica se l'utente ha effettuato un'operazione di aggiunta prodotto con successo.
     * @param editProduct Flag booleano che indica se l'utente ha effettuato un'operazione di modifica prodotto.
     * @param editSingleProduct Flag booleano che indica se l'utente ha effettuato un'operazione di modifica prodotto singolo.
     * @param salesUpdates Flag booleano che indica se l'utente ha effettuato un'operazione di aggiornamento delle vendite.
     * @param sendOffers Flag booleano che indica se l'utente ha effettuato un'operazione di invio offerte.
     * @param queryDone Flag booleano che indica se l'utente ha effettuato un'operazione di esecuzione query.
     * @param offerDone Flag booleano che indica se l'operazione di invio offerte è stata completata.
     * @param sendOffer Flag booleano che indica se è stata effettuata un'operazione di invio offerte.
     */
    public static void setAdminPage( @NotNull HttpServletRequest request, boolean addProduct, boolean productCreated,
                                     boolean editProduct, boolean editSingleProduct, boolean salesUpdates,
                                     boolean sendOffers, boolean queryDone, boolean offerDone, boolean sendOffer ) {
        request.setAttribute( "addproduct", addProduct );
        request.setAttribute( "productCreated", productCreated );
        request.setAttribute( "editproduct", editProduct );
        request.setAttribute( "editsingleproduct", editSingleProduct );
        request.setAttribute( "salesupdates", salesUpdates );
        request.setAttribute( "sendoffers", sendOffers );
        request.setAttribute( "queydone", queryDone );
        request.setAttribute( "offerDone", offerDone );
        request.setAttribute( "sendOffer", sendOffer );
        setListAndSumPrice( request );
    }


    /**
     * Metodo che imposta gli attributi per la lista delle categorie e il totale degli ordini effettuati, <br>
     * le entrate mensili, annuali e totali, nonché la lista degli ordini effettuati dagli utenti.
     *
     * @param request Oggetto HttpServletRequest che rappresenta la richiesta HTTP.
     */
    public static void setListAndSumPrice( @NotNull HttpServletRequest request ) {
        request.setAttribute( "categoryList", ManagerProduct.getCategoryList());
        try {
            request.setAttribute( "earningMonthly", OrderDAO.getSumPriceOrderMonthly() );
            request.setAttribute( "earningYears", OrderDAO.getSumPriceOrderYear() );
            request.setAttribute( "earningTotal", OrderDAO.getSumPriceOrderTotal() );
            request.setAttribute( "orderList", OrderDAO.getTotalOrdersUsersBeanList() );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in HelperControllerAdmin" + e );
        }
    }

    /**
     * Overload di {@link HelperControllerAdmin#setAdminPage(HttpServletRequest, boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean)}
     *
     * @param request Oggetto HttpServletRequest che rappresenta la richiesta HTTP.
     * @param value Flag booleano che sarà uguale per tutte.
     */
    public static void setAdminPage( @NotNull HttpServletRequest request, boolean value ){
        setAdminPage( request, value, value, value, value, value, value, value, value, value );
    }
}
