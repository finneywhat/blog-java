$(function() {
  $("#tagSelect input:checkbox").change(function() {
    var tagId = $(this).val();
    var tagName = $(this).parent().text().trim();
    if ($(this).is(':checked')) {
      $("#tags").append("<label id='" + tagName + "' class='checkbox-inline'>" +
          "<input checked type='hidden' name='tags' value='" + tagId + "'><span class='label label-info'>" + tagName + "</span><span class='tagRemove glyphicon glyphicon-remove'></span>" +
        "</label>");
      $(".tagRemove").last().click(function() {
        $(this).parents("label").remove();
      })
      console.log(tagId);
      console.log(tagName);
    } else {
      $("#"+tagName).remove();
      console.log("already checked");
    }
  })

  $("#newTagBtn").click(function() {
    var newTag = $("#newTagInput").val();
    $("#tags").append("<label id='" + newTag + "' class='checkbox-inline'>" +
        "<input checked type='hidden' name='tags' value='" + newTag + "'><span class='label label-info'>" + newTag + "</span><span class='tagRemove glyphicon glyphicon-remove'></span>" +
      "</label>");
    $(".tagRemove").last().click(function() {
      $(this).parents("label").remove();
    })
  })
})
