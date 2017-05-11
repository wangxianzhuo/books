<%--
  Created by IntelliJ IDEA.
  User: shangjie
  Date: 17-5-10
  Time: 下午3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书列表</title>
</head>
<body>
<form id="form-add">
    name: <input name="name">
    isbn: <input name="isbn">
    publisher: <input name="publisher">
    authors: <input name="authors">
    <button id="btn-add">add</button>
</form>
<hr>
<form id="form-edit">
    name: <input name="name" id="edit-name" disabled="disabled">
    isbn: <input name="isbn" id="edit-isbn" disabled="disabled">
    publisher: <input name="publisher" id="edit-publisher" disabled="disabled">
    authors: <input name="authors" id="edit-authors" disabled="disabled">
    <input id="edit-id" type="hidden">
    <input type="hidden" name="_method" value="PUT">
    <button id="btn-submit-edit" disabled="disabled">edit</button>
</form>
<hr>
<div id="notie-area"></div>

<table>
    <thead>
    <tr>
        <th>name</th>
        <th>isbn</th>
        <th>publisher</th>
        <th>authors</th>
        <th>action</th>
    </tr>
    </thead>
    <tbody id="table-body">
    <c:forEach var="book" items="${books}">
        <tr id="${book.id}">
            <td class="val-name">${book.name}</td>
            <td class="val-isbn">${book.isbn}</td>
            <td class="val-publisher">${book.publisher}</td>
            <td class="val-authors">${book.authors}</td>
            <td>
                <button class="btn-delete" data-id="${book.id}">delete</button>
                <button class="btn-edit" data-id="${book.id}">edit</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="/static/js/jquery.min.js"></script>
<%--<script src="/static/js/notie.min.js"></script>--%>
<script type="application/javascript">
    $(document).ready(function () {
        $('.btn-delete').click(function (e) {
            e.preventDefault();
            var id = $(this).attr("data-id");
            $.ajax({
                url: "/book/" + id,
                method: "DELETE",
                dataType: "json",
                success: function (result) {
//                    notie.alert(1, "delete success", 1.5);
                    notie(1, "delete success", 1.5);
                    $('#' + id).remove();
                },
                error: function (result) {
//                    notie.alert(3, "delete fail", 1.5);
                    notie(3, "delete fail", 1.5);
                }
            });
        });
        $('#btn-add').click(function (e) {
            e.preventDefault();
            $.ajax({
                url: "/book",
                method: "POST",
                dataType: "json",
                data: $('#form-add').serialize(),
                success: function (result) {
//                    notie.alert(1, "add success", 1.5);
                    notie(1, "add success", 1.5);
                    var text = "<tr id='" + result.id + "'>" +
                        "<td class='val-name'>" + result.name + "</td>" +
                        "<td class='val-isbn'>" + result.isbn + "</td>" +
                        "<td class='val-publisher'>" + result.publisher + "</td>" +
                        "<td class='val-authors'>" + result.authors + "</td>" +
                        "<td><button class='btn-delete' data-id='" + result.id + "'>delete</button> " +
                        "<button class='btn-edit' data-id='" + result.id + "'>edit</button></td>" +
                        "</tr>";
                    $('#table-body').prepend(text);
                    $('.btn-delete[data-id=' + result.id + ']').click(function (e) {
                        e.preventDefault();
                        var id = $(this).attr("data-id");
                        $.ajax({
                            url: "/book/" + id,
                            method: "DELETE",
                            dataType: "json",
                            success: function (result) {
//                                notie.alert(1, "delete success", 1.5);
                                notie(1, "delete success", 1.5);
                                $('#' + id).remove();
                            },
                            error: function (result) {
//                                notie.alert(3, "delete fail", 1.5);
                                notie(3, "delete fail", 1.5);
                            }
                        });
                    });
                    $('.btn-edit[data-id=' + result.id + ']').click(function (e) {
                        e.preventDefault();
                        $('#edit-name').val($('#' + result.id).children('.val-name').text());
                        $('#edit-isbn').val($('#' + result.id).children('.val-isbn').text());
                        $('#edit-publisher').val($('#' + result.id).children('.val-publisher').text());
                        $('#edit-authors').val($('#' + result.id).children('.val-authors').text());
                        $('#edit-id').val(result.id);
                        $('#btn-submit-edit').removeAttr("disabled");
                        $('#edit-name').removeAttr("disabled");
                        $('#edit-isbn').removeAttr("disabled");
                        $('#edit-publisher').removeAttr("disabled");
                        $('#edit-authors').removeAttr("disabled");
                    });
                }
            })
        });
        $('.btn-edit').click(function (e) {
            e.preventDefault();
            var id = $(this).attr("data-id");
            $('#edit-name').val($('#' + id).children('.val-name').text());
            $('#edit-isbn').val($('#' + id).children('.val-isbn').text());
            $('#edit-publisher').val($('#' + id).children('.val-publisher').text());
            $('#edit-authors').val($('#' + id).children('.val-authors').text());
            $('#edit-id').val(id);
            $('#btn-submit-edit').removeAttr("disabled");
            $('#edit-name').removeAttr("disabled");
            $('#edit-isbn').removeAttr("disabled");
            $('#edit-publisher').removeAttr("disabled");
            $('#edit-authors').removeAttr("disabled");
        });
        $('#btn-submit-edit').click(function (e) {
            var id = $('#edit-id').val();
            e.preventDefault();
            $.ajax({
                url: "/book/" + id,
                method: "POST",
                dataType: "json",
                data: $('#form-edit').serialize(),
                success: function (result) {
//                    notie.alert(1, "edit success", 1.5);
                    notie(1, "edit success", 1.5);
                    $('#' + id).children('.val-name').text(result.name);
                    $('#' + id).children('.val-isbn').text(result.isbn);
                    $('#' + id).children('.val-publisher').text(result.publisher);
                    $('#' + id).children('.val-authors').text(result.authors);
                    editFormClear();
                },
                error: function (result) {
//                    notie.alert(3, "edit fail", 1.5);
                    notie(3, "edit fail", 1.5);
                    editFormClear();
                }
            });
        });
        function editFormClear() {
            $('#edit-name').val("");
            $('#edit-isbn').val("");
            $('#edit-publisher').val("");
            $('#edit-authors').val("");
            $('#edit-id').val("");
            $('#btn-submit-edit').attr("disabled", "disabled");
            $('#edit-name').attr("disabled", "disabled");
            $('#edit-isbn').attr("disabled", "disabled");
            $('#edit-publisher').attr("disabled", "disabled");
            $('#edit-authors').attr("disabled", "disabled");
        }

        function notie(type, msg, stayTime) {
            var html;
            switch (type) {
                case 3:
                    html = "<div style='background-color: #d70600'>" + msg + "</div>";
                    break;
                case 2:
                    html = "<div style='background-color: #ffb721'>" + msg + "</div>";
                    break;
                case 1:
                default:
                    html = "<div style='background-color: #64f13e'>" + msg + "</div>";
                    break;
            }
            $('#notie-area').empty().html(html);
            setTimeout(function () {
                $('#notie-area').empty();
            }, stayTime * 1000);
        }
    });
</script>
</body>
</html>
