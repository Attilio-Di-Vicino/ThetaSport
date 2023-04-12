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
            function addToCart( productId, landingPage ) {
                // Crea un oggetto XMLHttpRequest
                var xhr = new XMLHttpRequest();
                // var numItemCart = 0;
                numItemCart = parseInt(document.getElementById("cartSection").getAttribute("numItemCart"));

                if (!isNaN(numItemCart)) {
                    numItemCart = parseInt(numItemCart);
                }/* else {
                    numItemCart = 0;
                }*/

                // Configura la richiesta GET alla servlet che gestisce l'aggiunta del prodotto
                xhr.open('GET', "AddCartServlet?landingPage=" + landingPage + "&codeProduct=" + productId);

                console.log("landingPage: " + landingPage);
                console.log("productId: " + productId);

                // Invia la richiesta GET alla servlet che gestisce l'aggiunta del prodotto
                xhr.send();

                // Gestisci la risposta della servlet
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        numItemCart += 1;
                        console.log(numItemCart);
                        // Aggiorna il valore di numItemCart nella pagina
                        document.getElementById( "cartSection" ).setAttribute( "numItemCart", numItemCart );
                        // Aggiorna la sezione del carrello della pagina con i dati aggiornati
                        document.getElementById( "cartSection" ).innerHTML = xhr.responseText;
                    }
                }
            }
        </script>

        <jsp:include page="component/navbar.jsp">
            <jsp:param name="name" value="${userBean.getName()}"/>
            <jsp:param name="isLogged" value="${isLogged}"/>
            <jsp:param name="login" value="${login}"/>
            <jsp:param name="numItemCart" value="${numItemCart}"/>
        </jsp:include>

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
                    <!-- Start Column 1 -->
                    <c:forEach var="product" items="${productBeanList}">
                        <div class="col-12 col-md-4 col-lg-3 mb-5">
                            <a class="product-item" href="#">
                                <form action="SingleProductServlet" method="get">
                                    <button style="background-color: transparent; border: none" name="codeProduct" value=${product.getCode()}>
                                        <img src="${pageContext.request.contextPath}/images/product/${product.getImage()}" class="img-fluid product-thumbnail">
                                    </button>
                                </form>
                                <h3 class="product-title">${product.getName()}</h3>
                                <strong class="product-price">$ ${product.getPrice()}</strong>

                                <span class="icon-cross">
                                    <img src="${pageContext.request.contextPath}/images/cross.svg" class="img-fluid">
                                </span>
                            </a>
                            <button id="addToCartButton" onclick="addToCart( '${product.getCode()}', 'index' )">Aggiungi al carrello</button>

                            <form action="AddCartServlet" method="get">
                                <input type="hidden" name="landingPage" value="index">
                                <button name="codeProduct" value=${product.getCode()}>Add Cart</button>
                            </form>
                            <form action="RemoveCartServlet" method="get">
                                <button name="codeProduct" value=${product.getCode()}>Remove Cart</button>
                                <input type="hidden" name="landingPage" value="index">
                            </form>
                        </div>
                    </c:forEach>
                    <%-- End Column 1 --%>
                </div>
            </div>
        </div>

        <jsp:include page="component/footer.jsp"></jsp:include>
        <jsp:include page="component/logout_modal.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
    </body>
</html>
