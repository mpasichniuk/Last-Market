(function() {
  var reviewForm = document.querySelector('#review-form');
  reviewForm.addEventListener('submit', function(event) {
    event.preventDefault();
    var formData = new FormData(reviewForm);
    var xhr = new XMLHttpRequest();
    xhr.open('POST', reviewForm.action, true);
    xhr.onload = function() {
      if (xhr.status === 200) {

        location.reload();
      } else {
        console.error('Error submitting review:', xhr.responseText);
      }
    };
    xhr.send(formData);
  });
})();
