$(function () {

  $('.news-like').click(function () {
    var _this, workoutId;
    _this = this;
    workoutId = $(this).data('id');
    $.post('/workouts/' + workoutId + '/like', function (likesCount) {
      var newsLike = $(_this).parent();
      newsLike.find('.news-likes-count').text(likesCount);
      newsLike.append('<span>Liked!</span>')
      $(_this).remove();
    })
  })

})