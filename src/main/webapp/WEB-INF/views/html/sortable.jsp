<!doctype html>
<html lang="en">
<head>
    <style>
        #sortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
        #sortable li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 18px; }
        #sortable li span { position: absolute; margin-left: -1.3em; }
    </style>

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {

            for(var i=1; i<3; i++){
                $( "#aaa" + i ).sortable( {
                    cancel: ".not-sortable",
                    cursor: "move",
                    item: $('table tbody tr'),
                    start: function(event, ui) {
                        console.log('start point : ' + ui.item.children('td:last').html());
                    },
                    update: function( event, ui ) {
                        $(this).children().each(function(index) {
                            $(this).find('td').last().html(index + 1)
                        });
                    },
                    stop: function(event, ui) {
                        ui.item.children('td:last').html('수정');
                    }
                });
            }

        } );
    </script>

    <style>
        table {
            border-collapse: collapse;
        }

        td, th {
            background: #fff;
            border-width: 0;
            border-bottom: 1px solid #B8B8B8;
            font-weight: normal !important;
            padding: 15px;
            text-align: left;
            vertical-align: middle;
        }

        body {
            color: #111;
            font-size: 16px;
            font-family: sans-serif;
        }
    </style>
</head>
<body>

<table>
    <thead>
    <tr>
        <th colspan="4">Original</th>
        <th colspan="4">table heading</th>
        <th colspan="4">table heading</th>
        <th colspan="4">table heading</th>
        <th colspan="4">table heading</th>
        <th colspan="4" class="sortTd">Current Pos</th>
    </tr>
    </thead>
    <tfoot>
    <tr class="ui-state-default">
        <th colspan="4">Original</th>
        <th colspan="4">table footer</th>
        <th colspan="4">table footer</th>
        <th colspan="4">table footer</th>
        <th colspan="4">table footer</th>
        <th colspan="4" class="sortTd">Current Pos</th>
    </tr>
    </tfoot>
    <tbody id="aaa1">
        <tr>
            <th colspan="4">First Row</th>
            <td colspan="4">data</td>
            <td colspan="4">data</td>
            <td colspan="4">data</td>
            <td colspan="4">data</td>
            <td colspan="4" class="sortTd">1</td>
        </tr>
        <tr class="not-sortable">
            <th colspan="4">Second Row</th>
            <td colspan="4">data</td>
            <td colspan="4">data</td>
            <td colspan="4">data</td>
            <td colspan="4">data</td>
            <td colspan="4" class="sortTd">2</td>
        </tr>
        <tr>
            <th colspan="4">Third Row</th>
            <td colspan="4">data</td>
            <td colspan="4">data</td>
            <td colspan="4">data</td>
            <td colspan="4">data</td>
            <td colspan="4" class="sortTd">3</td>
        </tr>
    </tbody>
    <tbody id="aaa2">
        <tr>
            <th colspan="4">Fourth Row</th>
            <td colspan="4">data1</td>
            <td colspan="4">data1</td>
            <td colspan="4">data1</td>
            <td colspan="4">data1</td>
            <td colspan="4" class="sortTd">4</td>
        </tr>
        <tr>
            <th colspan="4">Fifth Row</th>
            <td colspan="4">data2</td>
            <td colspan="4">data2</td>
            <td colspan="4">data2</td>
            <td colspan="4">data2</td>
            <td colspan="4" class="sortTd">5</td>
        </tr>
            <th colspan="4">Fifth Row</th>
            <td colspan="4">data3</td>
            <td colspan="4">data3</td>
            <td colspan="4">data3</td>
            <td colspan="4">data3</td>
            <td colspan="4" class="sortTd">6</td>
        </tr>
        <tr class="not-sortable">
            <th colspan="4">Fifth Row</th>
            <td colspan="4">data4</td>
            <td colspan="4">data4</td>
            <td colspan="4">data4</td>
            <td colspan="4">data4</td>
            <td colspan="4" class="sortTd">7</td>
        </tr>
    </tbody>
</table>


</body>
</html>