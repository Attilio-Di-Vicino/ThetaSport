(function addToCart( productId, landingPage, isLogged ) {
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
})()