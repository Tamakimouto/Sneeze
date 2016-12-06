<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sneeze</title>
        <meta name="author" content="Anthony Zheng, Zachary Phan, Robin Guice">
        <meta name="description" content="Insignificance at it's finest">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/home.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
        <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/vue/dist/vue.js"></script>
        <script src="js/home.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="head-strip text-center">
            <img src="img/sneezeLogo2.jpg">
            <span class="glyphicon glyphicon-log-out pull-right log-out"></span>
        </div>

        <div class="container" id="sneezeApp">
            <div class="row">
                <div class="col-sm-offset-3 col-sm-6">
                    <#list sneezes as sneeze>
                    <div class="media delay${sneeze?counter} animated" :class="animation">
                        <div class="media-body">
                            <h4 class="media-heading">${sneeze.user}</h4>
                            <p>${sneeze.msg}</p>
                        </div>
                    </div>
                    <#sep> <hr> </#sep>
                    </#list>
                </div>
            </div>
        </div>

        <nav class="navbar navbar-inverse navbar-fixed-bottom text-center">
            <form class="submission form-inline" action="Access">
                <div class="form-group col-xs-10">
                    <input type="text" class="form-control" id="msg" name="msg" placeholder="Sneeze about something!" style="width: 100%;">
                </div>
                <div class="form-group col-xs-2 buttons">
                    <input type="submit" class="btn btn-default" name="action" value="Sneeze">
                    <input type="submit" id="anon-btn" class="btn btn-default" name="action" value="Ninja!">
                </div>
            </form>
        </nav>

    </body>
</html>
