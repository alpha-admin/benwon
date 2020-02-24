/* gotop */

$(function() {
   /* 按下GoTop按鈕時的事件 */
   $('#gotop').click(function(){
       $('html,body').animate({ scrollTop: 0 }, 'slow');   /* 返回到最頂上 */
       return false;
   });

   /* 偵測卷軸滑動時，往下滑超過400px就讓GoTop按鈕出現 */
   $(window).scroll(function() {
       if ( $(this).scrollTop() > 200){
           $('#gotop').fadeIn();
       } else {
           $('#gotop').fadeOut();
       }
   });
});


/* +-button */

$(function() {
  // This button will increment the value
  $('.qtyplus').click(function(e) {
    // Stop acting like a button
    e.preventDefault();
    // Get the field name
    fieldName = $(this).attr('field');
    // Get its current value
    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
    // If is not undefined
    if (!isNaN(currentVal)) {
      // Increment
      $('input[name=' + fieldName + ']').val(currentVal + 1);
    } else {
      // Otherwise put a 0 there
      $('input[name=' + fieldName + ']').val(0);
    }
  });
  // This button will decrement the value till 0
  $(".qtyminus").click(function(e) {
    // Stop acting like a button
    e.preventDefault();
    // Get the field name
    fieldName = $(this).attr('field');
    // Get its current value
    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
    // If it isn't undefined or its greater than 0
    if (!isNaN(currentVal) && currentVal > 0) {
      // Decrement one
      $('input[name=' + fieldName + ']').val(currentVal - 1);
    } else {
      // Otherwise put a 0 there
      $('input[name=' + fieldName + ']').val(0);
    }
  });
});

/* +-button */


/* end */
