<!DOCTYPE html>
<html lang="pl-PL">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ankieta</title>
        <link rel="stylesheet" href="./css/style.css">
    </head>
    <body style="background-color: white; color: pink">
        <h1>Wybierz technologie, ktore znasz</h1>
        <form action="PollServlet" method="POST">
            <input type="checkbox" name="C" id="c">
            <label for="c">C</label><br/>
            
            <input type="checkbox" name="C++" id="cpp">
            <label for="cpp">C++</label><br/>
            
            <input type="checkbox" name="Java" id="java">
            <label for="java">Java</label><br/>
            
            <input type="checkbox" name="PHP" id="php">
            <label for="php">Php</label><br/>
            
            <input type="checkbox" name="JavaScript" id="js">
            <label for="js">Java Script</label><br/>
            
            <input type="checkbox" name="CSS" id="css">
            <label for="css">CSS</label><br/>
            
            <input type="checkbox" name="HTML" id="html">
            <label for="html">HTML</label><br/>
            
            <input type="submit" id="submit" value="Submit">
        </form>

        <br>
        <form action="ShowCsvServlet" method="POST">
            <input type="submit" value="Pobierz csv">
        </form>

        <br>
        <form action="ShowImageServlet" method="POST">
            <input type="submit" value="Pokaz obrazek">
        </form>

    </body>
</html>
