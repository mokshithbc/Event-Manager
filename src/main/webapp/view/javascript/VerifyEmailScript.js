function disableResendButton() {
    var resendButton = document.getElementById("resendButton");
    resendButton.disabled = true;
    resendButton.innerHTML = "Re-send Email (60s)";
    
    var countdown = 60;
    var interval = setInterval(function() {
        countdown--;
        if (countdown > 0) {
            resendButton.innerHTML = "Re-send Email (" + countdown + "s)";
        } else {
            clearInterval(interval);
            resendButton.innerHTML = "Re-send Email";
            resendButton.disabled = false;
        }
    }, 1000);
}

function checkResendTime() {
    var resendTime = localStorage.getItem('resendTime');
    if (resendTime) {
        var currentTime = new Date().getTime();
        var timeDifference = currentTime - resendTime;
        if (timeDifference < 60000) { // 60 seconds
            var countdown = Math.ceil((60000 - timeDifference) / 1000);
            var resendButton = document.getElementById("resendButton");
            resendButton.disabled = true;
            resendButton.innerHTML = "Re-send Email (" + countdown + "s)";
            
            var interval = setInterval(function() {
                countdown--;
                if (countdown > 0) {
                    resendButton.innerHTML = "Re-send Email (" + countdown + "s)";
                } else {
                    clearInterval(interval);
                    resendButton.innerHTML = "Re-send Email";
                    resendButton.disabled = false;
                }
            }, 1000);
        }
    }
}

window.onload = function() {
    checkResendTime();
}
