/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {            
    $("#containersSlider").hide();
    _createElements();
    $("#customWindow").hide();
    $("#canvas1").hide();
    var video1 = document.querySelector("#videoElement1");
    var video2 = document.querySelector("#videoElement2");
    $("#snapShot1").click(function () {        
        $("#videoElement1").hide();
        $("#canvas1").show();
        $('#Preview1').prop('disabled', false);
        $('#snapShot1').prop('disabled', true);
        var canvas1 = document.querySelector('#canvas1');
        var ctx = canvas1.getContext('2d');
        w = canvas1.width;
        h = canvas1.height;
        ctx.drawImage(video1, 0, 0, w, h);
        var dataUrl = canvas1.toDataURL("image/jpeg", 0.95);
        var image = new Image();
        image.src = dataUrl;        
         $("#camera1FileID").attr("value", image.src);
    });
    $("#snapShot2").click(function () {   
//         $("#videoElement2").hide();
//         $("#canvas2").show();
//         $('#Preview2').prop('disabled', false);
//         $('#snapShot2').prop('disabled', true);
//         var canvas2 = document.querySelector('#canvas2');
//         var ctx = canvas2.getContext('2d');
//         w = canvas2.width;
//         h = canvas2.height;
//         ctx.drawImage(video2, 0, 0, w, h);
//         var dataUrl = canvas2.toDataURL("image/jpeg", 0.95);
//         var image = new Image();
//         image.src = dataUrl;        
//         $("#camera1FileID").attr("value", image.src);
    });
    $("#Preview1").click(function () { 
        $("#canvas1").hide();
        $('#Preview1').prop('disabled', true);
        $('#snapShot1').prop('disabled', false);
        $("#videoElement1").show();
    });
    $("#Preview2").click(function () { 
//        $("#canvas2").hide();
//        $('#Preview2').prop('disabled', true);
//        $('#snapShot2').prop('disabled', false);
//        $("#videoElement2").show();
    });
    $("#registerButton").click(function () {
        console.log("pressed");
    });
    var source =
    {
        method: "GET",
        datatype: "json",
        datafields: [
            {name: 'CONTAINER_ID',type:'string'},
            {name: 'CONTAINER_OWNER',type:'string'},
            {name: 'CONTAINER_CHECK_IN',type:'string'},
            {name: 'CONTAINER_CHECK_OUT',type:'string'},
            {name: 'COMMENTS',type:'string'},
            {name: 'PAYMENT_STATUS',type:'string'},
            {name: 'PATH_TO_IMAGES',type:'string'}
        ],
        url: servletURL1,
        deleterow: function (rowid, commit) {
            // synchronize with the server - send delete command
            // call commit with parameter true if the synchronization with the server is successful 
            //and with parameter false if the synchronization failed.
            commit(true);
        },
        updaterow: function (rowid, newdata, commit) {
            // synchronize with the server - send update command
            // call commit with parameter true if the synchronization with the server is successful 
            // and with parameter false if the synchronization failed.
            commit(true);
        }
     };
    var dataAdapter = new $.jqx.dataAdapter(source);
    // initialize jqxGrid
    $("#jqxgrid").jqxGrid(
    {   
        theme: 'energyblue',
        width: 500,
        source: dataAdapter,      
        //selectionmode: 'singlecell',
        selectionmode:'singlerow',
        altrows: true,
        sortable: true,
        showtoolbar: true,
        rendertoolbar: function (toolbar) {
            var me = this;
            var container = $("<div style='margin: 5px;'></div>");
            toolbar.append(container);                    
            container.append('<input style="margin-left: 5px;" id="addrowbutton" type="button" value="Добавить контейнер" />');
            container.append('<input style="margin-left: 5px;" id="deleterowbutton" type="button" value="Удалить строку" />');
            container.append('<input style="margin-left: 5px;" id="updaterowbutton" type="button" value="Обновить строку" />');                    
            $("#deleterowbutton").jqxButton();
            $("#updaterowbutton").jqxButton();
            $("#addrowbutton").jqxButton();
            // add row.
            $("#addrowbutton").on('click', function () {
                $("#jqxWidget").hide();
                $("#trueHeader").hide();
                $('#customWindow').jqxWindow('open');
                launchCamera(video1);
            });  
            // update row.
            $("#updaterowbutton").on('click', function () {
                var datarow = generaterow();
                var selectedrowindex = $("#jqxgrid").jqxGrid('getselectedrowindex');
                var rowscount = $("#jqxgrid").jqxGrid('getdatainformation').rowscount;
                if (selectedrowindex >= 0 && selectedrowindex < rowscount) {
                    var id = $("#jqxgrid").jqxGrid('getrowid', selectedrowindex);
                    var commit = $("#jqxgrid").jqxGrid('updaterow', id, datarow);
                    $("#jqxgrid").jqxGrid('ensurerowvisible', selectedrowindex);
                }
            });                    
            // delete row.
            $("#deleterowbutton").on('click', function () {                        
                var selectedrowindex = $("#jqxgrid").jqxGrid('getselectedrowindex');
                console.log("selectedrowindex"+selectedrowindex);
                var rowscount = $("#jqxgrid").jqxGrid('getdatainformation').rowscount;
                if (selectedrowindex >= 0 && selectedrowindex < rowscount) {
                    var id = $("#jqxgrid").jqxGrid('getrowid', selectedrowindex);
                    var commit = $("#jqxgrid").jqxGrid('deleterow', id);
                }
            });
            },
        columns: [
          { text: 'ID', datafield: 'CONTAINER_ID', width: 100, align: 'center' },
          { text: 'Дата прибытия', datafield: 'CONTAINER_CHECK_IN', width: 200, align: 'center' },
          { text: 'Изображения', datafield: 'PATH_TO_IMAGES', width: 200, cellsalign: 'center', align: 'center', cellsformat: 'c2' }
          /*
          { text: 'ID', datafield: 'CONTAINER_ID', width: 50, align: 'center' },
          { text: 'Владелец', datafield: 'CONTAINER_OWNER', width: 100, align: 'center' },
          { text: 'Дата прибытия', datafield: 'CONTAINER_CHECK_IN', width: 150, align: 'center' },
          { text: 'Дата отъезда', datafield: 'CONTAINER_CHECK_OUT', width: 150, cellsalign: 'center', align: 'center' },
          { text: 'Комментарий', datafield: 'COMMENTS', width: 250, align: 'center', cellsalign: 'right', cellsformat: 'd' },
          { text: 'Оплата', datafield: 'PAYMENT_STATUS', width: 100, align: 'center', cellsalign: 'right' },
          { text: 'Изображения', datafield: 'PATH_TO_IMAGES', width: 200, cellsalign: 'center', align: 'center', cellsformat: 'c2' }
          */
        ]
        });
            $("#excelExport").jqxButton();
            $("#xmlExport").jqxButton();            
            $("#jsonExport").jqxButton();
            $("#pdfExport").jqxButton();
            /*
            $("#csvExport").jqxButton();
            $("#tsvExport").jqxButton();
            $("#htmlExport").jqxButton();
            $("#csvExport").click(function () {
                $("#jqxgrid").jqxGrid('exportdata', 'csv', 'jqxGrid');
            });
            $("#tsvExport").click(function () {
                $("#jqxgrid").jqxGrid('exportdata', 'tsv', 'jqxGrid');
            });
            $("#htmlExport").click(function () {
                $("#jqxgrid").jqxGrid('exportdata', 'html', 'jqxGrid');
            });
            */
            $("#excelExport").click(function () {
                $("#jqxgrid").jqxGrid('exportdata', 'xls', 'jqxGrid');           
            });
            $("#xmlExport").click(function () {
                $("#jqxgrid").jqxGrid('exportdata', 'xml', 'jqxGrid');
            });
            $("#jsonExport").click(function () {
                $("#jqxgrid").jqxGrid('exportdata', 'json', 'jqxGrid');
            });
            $("#pdfExport").click(function () {
                $("#jqxgrid").jqxGrid('exportdata', 'pdf', 'jqxGrid');
            });
            $("#jqxgrid").on("cellclick", function (event){
                // event arguments.
                var args = event.args;
                // row's bound index.
                var rowBoundIndex = args.rowindex;
                // row's visible index.
                var rowVisibleIndex = args.visibleindex;
                // right click.
                var rightclick = args.rightclick; 
                // original event.
                var ev = args.originalEvent;
                // column index.
                var columnindex = args.columnindex;
                console.log("column "+columnindex);
                // column data field.
                var dataField = args.datafield;
                
                //console.log("Here you go "+event.args.rowindex + ":" + event.args.datafield);
                if(columnindex==2){
                    // cell value
                    var value = args.value;
                    accessFTP(value);
                    $("#containersSlider").show();
                    var slideIndex = 1;
                    showDivs(slideIndex);
                    $("#jqxWidget").hide();
                    $("#trueHeader").hide();
                    $("#trueSide").hide();
                }                
             });
             $("#cancelButton").on('click', function () {
                $("#jqxWidget").show();
                $("#trueHeader").show();
                stopCamera();
             });
});

function _createElements() {
    var mainDemoContainer = $('#mainDemoContainer');
    var offset = mainDemoContainer.offset();        
    $('#customWindow').jqxWindow({  
        width: 800, 
        height: 700,
        maxHeight: 1000,
        resizable: false, 
        cancelButton: $('#cancelButton'), 
        showCloseButton: false,
        position: { x: offset.left, y: offset.top-220},
        initContent: function () {
            $("#dateTimeInput").jqxDateTimeInput({ formatString: "F", showTimeButton: true, width: '300px', height: '25px' });
            $('#registerButton').jqxButton({ width: '80px', disabled: false });
            $('#cancelButton').jqxButton({ width: '80px', disabled: false });
        }
    }
    );
    //launchCamera();
};
//CAMERA FUNCTIONS START
function launchCamera(video1) {    
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;
    if (navigator.getUserMedia) {       
        navigator.getUserMedia({video: true}, handleCamera, videoError);
    }
    function handleCamera(stream){
        video1.src = window.URL.createObjectURL(stream);    
    }
    function videoError(){
        console.log("JS VIDEO STREAM ERROR");
    }     
}
function stopCamera(){    
    navigator.getUserMedia({video: true}, stopStream, videoError);
    function stopStream(stream){      
        var MediaStream = stream.getTracks()[0];
        MediaStream.stop();
    }
    function videoError(){
        console.log("JS VIDEO STOP ERROR");
    }    
}
function dataURItoBlob(dataURI) {
  var byteString = atob(dataURI.split(',')[1]);
  var ab = new ArrayBuffer(byteString.length);
  var ia = new Uint8Array(ab);
  for (var i = 0; i < byteString.length; i++) { ia[i] = byteString.charCodeAt(i); }
  return new Blob([ab], { type: 'image/jpeg' });
}
//CAMERA FUNCTIONS END
//SLIDER FUNCTIONS START
var slideIndex = 1;
//showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function currentDiv(n) {
  showDivs(slideIndex = n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
     dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
  }
  x[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " w3-opacity-off";
}
function accessFTP(pathToFolder){
    //var file = new File ("/d/project/test_file.psd");
    //var ftp = new FtpConnection("ftp://192.168.1.2/PSA_IMAGES/1/1.jpg") ;
    //ftp.login("TALGAT", "astana777");
    var url = "ftp://TALGAT:ASTANA777@192.168.1.2"+pathToFolder;
    $('#imgBig').prop('src', url);
    $('#imgThumb').prop('src', url);
    //ftp.cd("project")
    //ftp.put(file,"test_file.psd") ;

    //ftp.close();
    //file.close();
}
function closeFunction(){
    $("#containersSlider").hide();
    $("#jqxWidget").show();
    $("#trueHeader").show();
    $("#trueSide").show();
}
//SLIDER FUNCTIONS END