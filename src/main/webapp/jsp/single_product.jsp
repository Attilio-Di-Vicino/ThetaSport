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
            <jsp:param name="title" value="${singleProduct.getName()}" />
        </jsp:include>
    </head>
    <body class="bg-light">
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
        <jsp:param name="homeMessageOne" value="ThetaSport: where AI meets sport"/>
        <jsp:param name="homeMessageTwo" value=" to maximize your performance"/>
        <jsp:param name="slogan" value="Challenge the limits of your sport with ThetaSport"/>
        <jsp:param name="botton" value="Shop Now"/>
        <jsp:param name="image" value="football-rugby.svg"/>
        <jsp:param name="requestHref" value="HomeServlet"/>
    </jsp:include>

    <div style="margin-top: 100px;"></div>
    <br>
    <!-- Open Content -->
    <section class="bg-light">
        <div class="container pb-5">
            <div class="row">
                <div class="col-lg-5 mt-5">
                    <div class="card mb-3">
                        <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/product/${singleProduct.getImage()}" alt="Card image cap" id="product-detail">
                    </div>
                </div>
                <!-- col end -->
                <div class="col-lg-7 mt-5">
                    <div class="card">
                        <div class="card-body">
                            <h1 class="h2">${singleProduct.getName()}</h1>
                            <p class="h3 py-2">$${singleProduct.getPrice()}</p>
                            <p class="py-2">
                                <i class="fa fa-star text-warning"></i>
                                <i class="fa fa-star text-warning"></i>
                                <i class="fa fa-star text-warning"></i>
                                <i class="fa fa-star text-warning"></i>
                                <i class="fa fa-star text-secondary"></i>
                                <span class="list-inline-item text-dark">Rating 4.8 | 36 Comments</span>
                            </p>
                            <ul class="list-inline">
                                <li class="list-inline-item">
                                    <h6>Category:</h6>
                                </li>
                                <li class="list-inline-item">
                                    <p class="text-muted"><strong>${singleProduct.getCategory()} ${singleProduct.getSubCategory()}</strong></p>
                                </li>
                            </ul>

                            <h6>Description:</h6>
                            <p>${singleProduct.getDescription()}.</p>
                            <ul class="list-inline">
                                <li class="list-inline-item">
                                    <h6>Avaliable Color :</h6>
                                </li>
                                <li class="list-inline-item">
                                    <p class="text-muted"><strong>White / Black</strong></p>
                                </li>
                            </ul>
                            <div class="row">
                                <div class="col-auto">
                                    <ul class="list-inline pb-3">
                                        <li class="list-inline-item">Size :

                                        </li>
                                        <li class="list-inline-item"><span class="btn btn-success btn-size">S</span></li>
                                        <li class="list-inline-item"><span class="btn btn-success btn-size">M</span></li>
                                        <li class="list-inline-item"><span class="btn btn-success btn-size">L</span></li>
                                        <li class="list-inline-item"><span class="btn btn-success btn-size">XL</span></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row pb-3">
                                <div class="col d-grid">
                                    <button onclick="addToCart( '${singleProduct.getCode()}', 'single_product', ${isLogged} )" class="btn btn-success btn-lg" name="codeProduct" value=${singleProduct.getCode()}>Add Cart</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Close Content -->
    <br><br>
    <jsp:include page="component/footer.jsp"></jsp:include>

    </body>
</html>