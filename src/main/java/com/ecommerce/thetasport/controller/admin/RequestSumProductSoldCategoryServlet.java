package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * La servlet RequestSumProductSoldCategoryServlet gestisce la richiesta di calcolo della somma dei prodotti venduti
 * per una specifica categoria e sottocategoria.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "RequestSumProductSoldCategoryServlet", value = "/RequestSumProductSoldCategoryServlet" )
public class RequestSumProductSoldCategoryServlet extends HttpServlet {


    /**
     * Gestisce la richiesta GET di visualizzazione della pagina di calcolo della somma dei prodotti venduti per
     * categoria e sottocategoria.
     *
     * @param request  l'oggetto HttpServletRequest che contiene la richiesta del client
     * @param response l'oggetto HttpServletResponse che contiene la risposta da inviare al client
     * @throws ServletException se la richiesta non può essere gestita correttamente
     * @throws IOException      se si verifica un errore di input/output durante la gestione della richiesta
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperControllerAdmin.setAdminPage( request, false, false, false, false,
                true, false, false, false, false );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }

    /**
     * Gestisce la richiesta POST di calcolo della somma dei prodotti venduti per categoria e sottocategoria.
     *
     * @param request  l'oggetto HttpServletRequest che contiene la richiesta del client
     * @param response l'oggetto HttpServletResponse che contiene la risposta da inviare al client
     * @throws ServletException se la richiesta non può essere gestita correttamente
     * @throws IOException      se si verifica un errore di input/output durante la gestione della richiesta
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        Category category = Category.valueOf( request.getParameter( "categoryRequest" ).toUpperCase().trim() );
        SubCategory subCategory = SubCategory.valueOf( request.getParameter( "subCategoryRequest" ).toUpperCase().trim() );
        int result;
        try {
            result = ProductDAO.getSumSoldItemsCategory( category, subCategory );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in RequestSumProductSoldCategoryServlet" + e );
        }
        HelperControllerAdmin.setAdminPage( request, false, false, false, false,
                true, false, true, false, false );
        request.setAttribute( "categotySum", result );
        request.setAttribute( "category", category );
        request.setAttribute( "subCategory", subCategory );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
