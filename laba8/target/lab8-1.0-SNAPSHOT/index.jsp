<!DOCTYPE html>
<html>
<head>
    <title>Forms</title>
    <script>
        function showFields() {
            var shapeType = document.getElementById("shapeType").value;
            var fields = document.getElementsByClassName("shape-field");
            for (var i = 0; i < fields.length; i++) {
                fields[i].style.display = "none";
            }
            document.getElementById(shapeType + "-fields").style.display = "block";
        }
    </script>

</head>
<body>
<h1>Shape Calculator</h1>
<form action="calculate" method="post">
    <label for="shapeType">Shape Type:</label>
    <select name="shapeType" id="shapeType" onchange="showFields()">
        <option value="parallelepiped">Parallelepiped</option>
        <option value="cube">Cube</option>
        <option value="sphere">Sphere</option>
        <option value="tetrahedron">Tetrahedron</option>
        <option value="torus">Torus</option>
    </select>

    <br><br>

    <div id="parallelepiped-fields" style="display: none;">
        <label for="length">Length:</label>
        <input type="text" name="length" id="length">

        <br>

        <label for="width">Width:</label>
        <input type="text" name="width" id="width">

        <br>

        <label for="height">Height:</label>
        <input type="text" name="height" id="height">

        <br><br>
    </div>

    <div id="cube-fields" style="display: none;">
        <label for="side">Side:</label>
        <input type="text" name="side" id="side">

        <br><br>
    </div>

    <div id="sphere-fields" style="display: none;">
        <label for="radius">Radius:</label>
        <input type="text" name="radius" id="radius">

        <br><br>
    </div>

    <div id="tetrahedron-fields" style="display: none;">
        <label for="side">Side:</label>
        <input type="text" name="side1" id="side1">

        <br><br>
    </div>

    <div id="torus-fields" style="display: none;">
        <label for="majorRadius">Major Radius:</label>
        <input type="text" name="majorRadius" id="majorRadius">

        <br>

        <label for="minorRadius">Minor Radius:</label>
        <input type="text" name="minorRadius" id="minorRadius">

        <br><br>
    </div>

    <input type="submit" value="Calculate">

    <p>Result: ${volume}</p>
</form>
</body>
</html>