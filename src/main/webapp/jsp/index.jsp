<%--
  Created by IntelliJ IDEA.
  Project: ThetaSport
  Version: 1.0
  Current year: 2023
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="component/header.jsp" >
        <jsp:param name="title" value="Theta Sport" />
    </jsp:include>
</head>
    <body>
        <script>
            function addToCart( productId, landingPage, isLogged ) {
                // Crea un oggetto XMLHttpRequest
                var xhr = new XMLHttpRequest();
                if ( isLogged === 0 ) {
                    // Reindirizza l'utente a "login.jsp"
                    window.location.href = "LoginServlet";
                } else {
                    var numItemCart = 0;
                    numItemCart = Number( document.getElementById( "cartSection" ).innerHTML );

                    if (!isNaN(numItemCart)) {
                        numItemCart = Number(numItemCart);
                    }

                    // Configura la richiesta GET alla servlet che gestisce l'aggiunta del prodotto
                    xhr.open( 'GET', "AddCartServlet?landingPage=" + landingPage + "&codeProduct=" + productId );

                    console.log( "landingPage: " + landingPage );
                    console.log( "productId: " + productId );

                    // Invia la richiesta GET alla servlet che gestisce l'aggiunta del prodotto
                    xhr.send();

                    // Gestisci la risposta della servlet
                    xhr.onreadystatechange = function() {
                        if ( xhr.readyState === 4 && xhr.status === 200 ) {
                            numItemCart += 1;
                            console.log( numItemCart );
                            document.getElementById( "cartSection" ).innerHTML = numItemCart;
                        }
                    }
                }
            }
        </script>

        <jsp:include page="component/navbar.jsp"></jsp:include>

        <jsp:include page="component/hero_section.jsp">
            <jsp:param name="homeMessageOne" value="Challenge the limits"/>
            <jsp:param name="homeMessageTwo" value=" of your sport with ThetaSport"/>
            <jsp:param name="slogan" value="Challenge the limits of your sport with ThetaSport"/>
            <jsp:param name="botton" value="Shop Now"/>
            <jsp:param name="image" value="bicycle.svg"/>
            <jsp:param name="requestHref" value="HomeServlet"/>
        </jsp:include>

        <!-- List Product -->
        <div class="untree_co-section product-section before-footer-section">
            <div class="container">
                <div class="row">
                    <!-- Start Column -->
                    <c:forEach var="product" items="${productBeanList}">
                        <div class="col-12 col-md-4 col-lg-3 mb-5">
                            <a class="product-item">
                                <form action="SingleProductServlet" method="get">
                                    <button style="background-color: transparent; border: none" name="codeProduct" value=${product.getCode()}>
                                        <img src="${pageContext.request.contextPath}/images/product/${product.getImage()}" class="img-fluid product-thumbnail">
                                    </button>
                                </form>
                                <h3 class="product-title">${product.getName()}</h3>
                                <strong class="product-price">$ ${product.getPrice()}</strong>

                                <span class="icon-cross" onclick="addToCart( '${product.getCode()}', 'index', ${isLogged} )">
                                    <img src="${pageContext.request.contextPath}/images/cross.svg" class="img-fluid">
                                </span>
                            </a>
                        </div>
                    </c:forEach>
                    <%-- End Column --%>
                </div>
            </div>
        </div>

        <jsp:include page="component/footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
    </body>
</html>
