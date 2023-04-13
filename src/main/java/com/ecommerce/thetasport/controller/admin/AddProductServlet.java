package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.Director;
import com.ecommerce.thetasport.service.productabstractfactory.Product;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.jetbrains.annotations.NotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@WebServlet( name = "AddProductServlet", value = "/AddProductServlet" )
@MultipartConfig( maxFileSize = 1024 * 1024 * 10 ) // Imposta la dimensione massima del file a 10 MB
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        ProductBean productBean = new ProductBean();
        Part file = request.getPart( "image" );
        String imageFileName = file.getSubmittedFileName();  // get selected image file name
        System.out.println( "Selected Image File Name : " + imageFileName );
        // essendo il localhost bisogna inserire il proprio path
        String uploadPath = "/home/attilio/Scrivania/ThetaSport/src/main/webapp/images/product/ " + imageFileName;  // upload path where we have to upload our actual image
        System.out.println( "Upload Path : " + uploadPath );
        // Uploading our selected image into the images folder
        try {
            FileOutputStream fos = new FileOutputStream( uploadPath );
            InputStream is = file.getInputStream();
            byte[] data=new byte[ is.available() ];
            is.read( data );
            fos.write( data );
            fos.close();
        } catch( Exception e ) {
            e.printStackTrace();
        }
        String category = request.getParameter("category");
        try {
            if ( category == null ){
                throw new NullPointerException( "Category is null in AddProductServlet" );
            }
            productBean.setCategory( Category.valueOf( category.toUpperCase().trim() ) );
            productBean.setSubCategory( SubCategory.valueOf( request.getParameter( "subcategory" ).toUpperCase().trim() ) );
            productBean.setName( request.getParameter( "name" ) );
            productBean.setDescription( request.getParameter( "description" ) );
            productBean.setStock( Integer.parseInt( request.getParameter( "stock" ) ) );
            productBean.setPrice( Double.parseDouble( request.getParameter( "price" ) ) );
            productBean.setImage( imageFileName );
            Product product;
            product = Director.createProduct( productBean );
            try {
                if ( product == null ) {
                    throw new NullPointerException( "Product is null in AddProductServlet" );
                }
                ProductDAO.insertProduct( product );
                HttpSession session = request.getSession( false );
                if ( session == null ) {
                    session = request.getSession();
                }
                session.setAttribute( "productCreated", true );
                request.setAttribute( "newProduct", product );
                HelperControllerAdmin.setListAndSumPrice( request );
                request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
            } catch ( NullPointerException e ) {
                e.printStackTrace();
            }
        } catch ( NullPointerException e ) {
            e.printStackTrace();
        }
    }
}
