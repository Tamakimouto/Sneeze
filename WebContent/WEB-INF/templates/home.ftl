<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sneeze</title>
        <meta name="author" content="Anthony Zheng, Zachary Phan, Robin Guice">
        <meta name="description" content="Insignificance at it's finest">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/home.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
        <link href='https://fonts.googleapis.com/css?family=Cabin+Sketch' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <#list sneezes as sneeze>
            <div class="row">
                <div class="sneezeContainer col-sm-offset-3 col-sm-6 animated zoomInDown">
                    <p>${sneeze.msg}</p>
                    <p>${sneeze.user}</p>
                </div>
            </div>
            </#list>
        </div>

        <nav class="navbar navbar-fixed-bottom">
            <div class="container-fluid">
                <div class="col-xs-6">
                    <form class="submission form-inline" action="Access">
                        <div class="form-group col-xs-8">
                            <input type="text" class="form-control" id="msg" name="msg" placeholder="Sneeze!" style="width: 100%;">
                        </div>
                        <div class="form-group col-xs-4">
                            <input type="hidden" name="form" value="sneeze">
                            <input type="submit" class="btn btn-default" value="submit">
                        </div>
                    </form>
                </div>
                <div class="col-xs-6">
                    <form class="submission form-inline">
                        <div class="col-xs-11 text-right">
                            <input type="hidden" name="form" value="refresh">
                            <input type="button" class="btn btn-primary" value="Refresh">
                        </div>
                    </form>
                </div>
            </div>
        </nav>

    </body>
</html>
