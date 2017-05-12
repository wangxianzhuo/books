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
    parentId:
    <select name="parentId">
        <option value="" class="opt-parentId">empty</option>
        <c:forEach var="library" items="${libraries}">
            <option value="${library.id}" class="opt-${library.id}">${library.name} - ${library.id}</option>
        </c:forEach>
    </select>
    weight: <input name="weight">
    capacity: <input name="capacity">
    size: <input name="size">
    <button id="btn-add">add</button>
</form>
<hr>
<form id="form-edit">
    name: <input name="name" id="edit-name" disabled="disabled">
    parentId:
    <select name="parentId" id="edit-parentId" disabled="disabled">
        <option value="" class="opt-edit-parentId opt-edit-parentId-empty">empty</option>
        <c:forEach var="library" items="${libraries}">
            <option value="${library.id}" class="opt-edit-parentId opt-${library.id}"
                    id="edit-opt-${library.id}">${library.name} - ${library.id}</option>
        </c:forEach>
    </select>
    weight: <input name="weight" id="edit-weight" disabled="disabled">
    capacity: <input name="capacity" id="edit-capacity" disabled="disabled">
    size: <input name="size" id="edit-size" disabled="disabled">
    <input id="edit-id" type="hidden">
    <input type="hidden" name="_method" value="PUT">
    <button id="btn-submit-edit" disabled="disabled">edit</button>
</form>
<hr>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>parentId</th>
        <th>weight</th>
        <th>capacity</th>
        <th>size</th>
        <th>action</th>
    </tr>
    </thead>
    <tbody id="table-body">
    <c:forEach var="library" items="${libraries}">
        <tr id="${library.id}">
            <td class="val-id">${library.id}</td>
            <td class="val-name">${library.name}</td>
            <td class="val-parentId">${library.parentId}</td>
            <td class="val-weight">${library.weight}</td>
            <td class="val-capacity">${library.capacity}</td>
            <td class="val-size">${library.size}</td>
            <td>
                <button class="btn-delete" data-id="${library.id}">delete</button>
                <button class="btn-edit" data-id="${library.id}">edit</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="notie-area"></div>
<script src="/static/js/jquery.min.js"></script>
<%--<script src="/static/js/notie.min.js"></script>--%>
<script type="application/javascript">
    $(document).ready(function () {
        function editFormClear() {
            $('#edit-name').val("");
            $('#edit-parentId').val("");
            $('#edit-weight').val("");
            $('#edit-capacity').val("");
            $('#edit-size').val("");
            $('#edit-id').val("");
            $('#btn-submit-edit').attr("disabled", "disabled");
            $('#edit-name').attr("disabled", "disabled");
            $('#edit-parentId').attr("disabled", "disabled");
            $('#edit-weight').attr("disabled", "disabled");
            $('#edit-capacity').attr("disabled", "disabled");
            $('#edit-size').attr("disabled", "disabled");

            $('.opt-edit-parentId').each(function (index, value) {
                $(value).removeAttr("disabled");
            })
        }

        function deleteEvent(cxt) {
            editFormClear();
            var id = $(cxt).attr("data-id");
            $.ajax({
                url: "/library/" + id,
                method: "DELETE",
                dataType: "json",
                success: function (result) {
//                    notie.alert(1, "delete success", 1.5);
                    notie(1, "delete success", 1.5);
                    $('#' + id).remove();
                    $('.opt-' + id).remove();
                },
                error: function (result) {
//                    notie.alert(3, "delete fail", 1.5);
                    notie(3, "delete fail", 1.5);
                }
            });
        }

        function editEvent(cxt) {
            editFormClear();
            var id = $(cxt).attr("data-id");
            $('#edit-name').val($('#' + id).children('.val-name').text());
            $('#edit-parentId').val($('#' + id).children('.val-parentId').text());
            $('#edit-weight').val($('#' + id).children('.val-weight').text());
            $('#edit-capacity').val($('#' + id).children('.val-capacity').text());
            $('#edit-size').val($('#' + id).children('.val-size').text());
            $('#edit-id').val(id);
            $('#btn-submit-edit').removeAttr("disabled");
            $('#edit-name').removeAttr("disabled");
            $('#edit-parentId').removeAttr("disabled");
            $('#edit-weight').removeAttr("disabled");
            $('#edit-capacity').removeAttr("disabled");
            $('#edit-size').removeAttr("disabled");
            console.log($('#edit-opt-' + id))
            $('#edit-opt-' + id).attr("disabled", "disabled");
        }

        $('.btn-delete').click(function (e) {
            e.preventDefault();
            deleteEvent(this);
        });
        $('#btn-add').click(function (e) {
            e.preventDefault();
            $.ajax({
                url: "/library",
                method: "POST",
                dataType: "json",
                data: $('#form-add').serialize(),
                success: function (result) {
//                    notie.alert(1, "add success", 1.5);
                    notie(1, "add success", 1.5);
                    var text = "<tr id='" + result.id + "'>" +
                        "<td class='val-id'>" + result.id + "</td>" +
                        "<td class='val-name'>" + result.name + "</td>" +
                        "<td class='val-parentId'>" + result.parentId + "</td>" +
                        "<td class='val-weight'>" + result.weight + "</td>" +
                        "<td class='val-capacity'>" + result.capacity + "</td>" +
                        "<td class='val-size'>" + result.size + "</td>" +
                        "<td><button class='btn-delete' data-id='" + result.id + "'>delete</button> " +
                        "<button class='btn-edit' data-id='" + result.id + "'>edit</button></td>" +
                        "</tr>";
                    $('#table-body').prepend(text);
                    $('.opt-parentId').after("<option value='" + result.id + "' class='opt-" + result.id + "'>" + result.name + " - " + result.id + "</option>");
                    $('.opt-edit-parentId.opt-edit-parentId-empty')
                        .after("<option value='" + result.id + "' class='opt-edit-parentId opt-" + result.id.trim() + "' id='edit-opt-" + result.id + "'>" + result.name + " - " + result.id + "</option>");
                    $('.btn-delete[data-id=' + result.id + ']').click(function (e) {
                        e.preventDefault();
                        deleteEvent(this);
                    });
                    $('.btn-edit[data-id=' + result.id + ']').click(function (e) {
                        e.preventDefault();
                        editEvent(this);
                    });
                },
                error: function (result) {
                    notie(3, result.responseJSON.message, 1.5);
                }
            })
        });

        $('.btn-edit').click(function (e) {
            e.preventDefault();
            editEvent(this);
        });
        $('#btn-submit-edit').click(function (e) {
            var id = $('#edit-id').val();
            e.preventDefault();
            $.ajax({
                url: "/library/" + id,
                method: "POST",
                dataType: "json",
                data: $('#form-edit').serialize(),
                success: function (result) {
//                    notie.alert(1, "edit success", 1.5);
                    notie(1, "edit success", 1.5);
                    $('#' + id).children('.val-name').text(result.name);
                    $('#' + id).children('.val-parentId').text(result.parentId);
                    $('#' + id).children('.val-weight').text(result.weight);
                    $('#' + id).children('.val-capacity').text(result.capacity);
                    $('#' + id).children('.val-size').text(result.size);
                    editFormClear();
                    $('.opt-' + id).text(result.name + " - " + id);
                },
                error: function (result) {
//                    notie.alert(3, "edit fail", 1.5);
                    notie(3, "edit fail", 1.5);
                    editFormClear();
                }
            });
        });

        function notie(type, msg, stayTime) {
            var html;
            switch (type) {
                case 3:
                    html = "<div style='background-color: #ff574d'>" + msg + "</div>";
                    break;
                case 2:
                    html = "<div style='background-color: #ffb721'>" + msg + "</div>";
                    break;
                case 1:
                default:
                    html = "<div style='background-color: #92f181'>" + msg + "</div>";
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
