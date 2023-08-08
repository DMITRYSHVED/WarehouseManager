<!-- Add the script inside the body or just before the closing body tag -->

    document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('#elastic').oninput = function () {
        let val = this.value.toUpperCase().trim();
        let elasticItems = document.querySelectorAll('.elastic tr')
        if (val !== '') {
            elasticItems.forEach(function (elem) {
                if (elem.innerText.toUpperCase().search(val) === -1) {
                    elem.classList.add('hide');
                } else {
                    elem.classList.remove('hide');
                }
            });
        } else {
            elasticItems.forEach(function (elem) {
                elem.classList.remove('hide');
            });
        }
    }
});
document.addEventListener('DOMContentLoaded', function() {
        document.querySelector('#elastic1').oninput = function () {
            let val1 = this.value.toUpperCase().trim();
            let elasticItems = document.querySelectorAll('.elastic1 tr')
            if (val1 !== '') {
                elasticItems.forEach(function (elem) {
                    if (elem.innerText.toUpperCase().search(val1) === -1) {
                        elem.classList.add('hide');
                    } else {
                        elem.classList.remove('hide');
                    }
                });
            } else {
                elasticItems.forEach(function (elem) {
                    elem.classList.remove('hide');
                });
            }
        }
    }
);
document.addEventListener('DOMContentLoaded', function() {
        document.querySelector('#elastic2').oninput = function () {
            let val1 = this.value.toUpperCase().trim();
            let elasticItems = document.querySelectorAll('.elastic2 tr')
            if (val1 !== '') {
                elasticItems.forEach(function (elem) {
                    if (elem.innerText.toUpperCase().search(val1) === -1) {
                        elem.classList.add('hide');
                    } else {
                        elem.classList.remove('hide');
                    }
                });
            } else {
                elasticItems.forEach(function (elem) {
                    elem.classList.remove('hide');
                });
            }
        }
    }
);
