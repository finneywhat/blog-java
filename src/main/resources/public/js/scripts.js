$(function() {
  $('[data-toggle="popover"]').popover()

  $(".tagRemove").click(function() {
    $(this).parents("label").remove();
  })

  $("#tagSelect option").click(function() {
    var tagId = $(this).val();
    var tagName = $(this).text().trim();
    if (!($("#"+tagName).length)) {
      $("#tags").append("<label id='" + tagName + "' class='checkbox-inline'>" +
          "<input checked type='hidden' name='tags' value='" + tagId + "'><span class='label label-info'>" + tagName + "</span><span class='tagRemove glyphicon glyphicon-remove'></span>" +
        "</label>");
      $(".tagRemove").last().click(function() {
        $(this).parents("label").remove();
      })
      console.log(tagId);
      console.log(tagName);
    } else {
    }
  })

  $("#newTagBtn").click(function() {
    var newTag = $("#newTagInput").val();
    if (!($("#"+newTag).length)) {
      $("#tags").append("<label id='" + newTag + "' class='checkbox-inline'>" +
      "<input checked type='hidden' name='tags' value='" + newTag + "'><span class='label label-info'>" + newTag + "</span><span class='tagRemove glyphicon glyphicon-remove'></span>" +
      "</label>");
      $(".tagRemove").last().click(function() {
        $(this).parents("label").remove();
      })
    }
    $("#newTagInput").val("");
  })
})
