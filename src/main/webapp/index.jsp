<html>
<head>
    <title>Counter Application</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function doAjax(increase = false) {
            $.ajax({
                type: 'GET',
                url: 'count?increase=' + increase,
                success: function (data) {
                    $("#counter").html(data);
                },
                complete: function (data) {
                    // Schedule the next
                    setTimeout(function() {
                        doAjax(false);
                    }, interval);
                }
            });
        }
        var interval = 1000 * 10;  // 10 seconds
        $(document).ready(function () {
            doAjax(true);
        });
    </script>
</head>
<body>

<h1>Counter Application</h1>

<div><b>Count:</b> <span id="counter"></span></div>
<div><b>Version:</b> <%= System.getenv("PROJECT_VERSION") %></div>
<div><b>EC2 instance id:</b> <%= System.getenv("EC2_INSTANCE_ID") %></div>


</body>
</html>