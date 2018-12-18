<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/_header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/_menu.jsp"></jsp:include>

<h3>Skill List</h3>

<p style="color: red;">${errorString}</p>



<table>
    <tr>
        <td>

            <table border="3" cellpadding="10" cellspacing="5">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${skillList}" var="skill">
                    <tr>
                        <td>${skill.id}</td>
                        <td>
                            <form action="skill" method="POST">
                                <input name="id" id="id" type="hidden" value="${skill.id}"/>
                                <input name="name" id="name" type="text" value="${skill.name}"/>
                                <input type="submit" value="Edit" name="edit"/>
                            </form>
                        </td>
                        <td>
                            <form action="skill" method="POST">
                                <input name="id" type="hidden" value="${skill.id}"/>
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>


        </td>

        <td>

            <form action="skill" method="POST">
                <h3>Insert form</h3>
                <table id="myTable" class=" table order-list" border="3" cellpadding="2" cellspacing="1">
                    <thead>
                    <tr>
                        <td>Name</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="col-sm-10">
                            <input type="text" name="name0" class="form-control"/>
                        </td>
                        <td class="col-sm-1"><a class="deleteRow"></a>

                        </td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="5" style=" text-align: left;">
                            <input name="counter" id="counter1" type="hidden" value="1"/>
                            <input type="button" class="btn btn-md btn-primary " id="addrow" value="Add Row"/>
                            <input type="submit" class="btn btn-md btn-success " id="insert" value="INSERT TO DB"/>
                        </td>

                    </tr>
                    <tr>
                    </tr>
                    </tfoot>
                </table>
            </form>


        </td>
    </tr>
</table>







<jsp:include page="/WEB-INF/jsp/_footer.jsp"></jsp:include>


</body>

<script>
    $(document).ready(function () {
        var counter = 1;


        $("#insert").on("click", function () {
            document.getElementById("counter1").value = counter;
        });
        $("#addrow").on("click", function () {
            var newRow = $("<tr>");
            var cols = "";



            cols += '<td><input type="text" class="form-control" name="name' + counter + '"/></td>';
            cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';



            newRow.append(cols);
            $("table.order-list").append(newRow);
            counter++;
        });


        $("table.order-list").on("click", ".ibtnDel", function (event) {
            $(this).closest("tr").remove();
            counter -= 1
        });


    });


    function calculateRow(row) {
        var price = +row.find('input[name^="price"]').val();

    }

    function calculateGrandTotal() {
        var grandTotal = 0;
        $("table.order-list").find('input[name^="price"]').each(function () {
            grandTotal += +$(this).val();
        });
        $("#grandtotal").text(grandTotal.toFixed(2));
    }
</script>
</html>
