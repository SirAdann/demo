var puesto = document.getElementById("puestos");
var modalidad = document.getElementById("modalidad");

	const $btnExportar = document.querySelector("#btnExportar"),
    $tabla = document.querySelector("#tabla");

if(modalidad != null) {
	modalidad.addEventListener("click", function() {
	    if (modalidad.value ==1) {
        document.getElementById("plat").setAttribute("class", "mostrar");
        document.getElementById("plat").setAttribute("required","required");
        document.getElementById("lug").setAttribute("class", "ocultar");
        document.getElementById("lug").removeAttribute("required");


	        setTimeout(() => {}, 300);
	    } else {
	    document.getElementById("plat").setAttribute("class", "ocultar");
	    document.getElementById("lug").setAttribute("class", "mostrar");
        document.getElementById("plat").removeAttribute("required");
        document.getElementById("lug").setAttribute("required", "required");

	      


	    }
	});
}

function ajustarform(){
	
	if(modalidad != null) {
	    if (modalidad.value ==1) {
        document.getElementById("plat").setAttribute("class", "mostrar");
        document.getElementById("plat").setAttribute("required","required");
        document.getElementById("lug").setAttribute("class", "ocultar");
        document.getElementById("lug").removeAttribute("required");


	        setTimeout(() => {}, 300);
	    } else {
	    document.getElementById("plat").setAttribute("class", "ocultar");
	    document.getElementById("lug").setAttribute("class", "mostrar");
        document.getElementById("plat").removeAttribute("required");
        document.getElementById("lug").setAttribute("required", "required");

	      


	    }
	
}
}




if(puesto != null) {
	puesto.addEventListener("click", function() {
	    if (puesto.innerText == "Mostrar") {
	        document.getElementById("listado-puestos").setAttribute("class", "listado-puestos-mostrar");
	        puesto.innerHTML = "Ocultar";
	        setTimeout(() => {}, 300);
	    } else {
	        document.getElementById("listado-puestos").setAttribute("class", "listado-puestos");
	        puesto.innerHTML = "Mostrar";
	    }
	});
}



function googleTranslateElementInit() {
       new google.translate.TranslateElement({ pageLanguage: 'en' }, 'google_translate_element');
   }
    
var flags = document.getElementsByClassName('flag_link');
Array.prototype.forEach.call(flags, function(e){
  e.addEventListener('click', function(){
    var lang = e.getAttribute('data-lang'); 
    var languageSelect = document.querySelector("select.goog-te-combo");    
    languageSelect.value = lang; 
    languageSelect.dispatchEvent(new Event("change"));
  }); 
});

$btnExportar.addEventListener("click", function() {
    let tableExport = new TableExport($tabla, {
    exportButtons: false, // No queremos botones
    filename: "Mi tabla de Excel", //Nombre del archivo de Excel
    sheetname: "Mi tabla de Excel", //Título de la hoja
});
let datos = tableExport.getExportData();
let preferenciasDocumento = datos.tabla.xlsx;
tableExport.export2file(preferenciasDocumento.data, preferenciasDocumento.mimeType, preferenciasDocumento.filename, preferenciasDocumento.fileExtension, preferenciasDocumento.merges, preferenciasDocumento.RTL, preferenciasDocumento.sheetname);
});


function exportTableToExcel(tableID, filename = ''){
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    
    // Specify file name
    filename = filename?filename+'.xls':'excel_data.xls';
    
    // Create download link element
    downloadLink = document.createElement("a");
    
    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
    
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
}

function validacion() { 
	   

	if (document.getElementById("coordinadores").checked==false && document.getElementById("jefes").checked==false
	&& document.getElementById("auditores").checked==false && document.getElementById("administrativos").checked==false
	&& document.getElementById("academicos").checked==false
	)    { // Si esta comprobación no se cumple... 
	
	alert('Seleccione mínimo un grupo de Funcionarios');
	
	return false; }
	
	//else{
		
	//	 startUpload();
		
	//} 
	}
	
	

        
function validarEmail() {
	var emailField = document.getElementById('correo');
	var validEmail =  /^\w+([.-_+]?\w+)*@(?:|uabc)\.(?:|edu.mx)+$/;
	
  if (validEmail.test(emailField.value)){
  // alert("La dirección de email " + document.getElementById("correo") + " es correcta.");
  } else {
	//alert(emailField.value);
   alert("La dirección de email "+emailField.value+" es incorrecta ingrese un correo con terminación; @uabc.edu.mx");
  return false;
  
  }
  

}

 function debug(s) {
    var debug = document.getElementById('debug');
    if (debug) {
      debug.innerHTML = debug.innerHTML + '<br/>' + s;
    }
  }

 function onUploadComplete(e) {
    totalUploaded += document.getElementById('files').files[filesUploaded].size;
    filesUploaded++;
    debug('complete ' + filesUploaded + " of " + fileCount);
    debug('totalUploaded: ' + totalUploaded);
    if (filesUploaded < fileCount) {
      uploadNext();
    } else {
      var bar = document.getElementById('bar');
      bar.style.width = '100%';
      bar.innerHTML = '100% complete';
      alert('Finished uploading file(s)');
    }
  }
  
  function onUploadProgress(e) {
    if (e.lengthComputable) {
      var percentComplete = parseInt((e.loaded + totalUploaded) * 100 / totalFileLength);
      var bar = document.getElementById('bar');
      bar.style.width = percentComplete + '%';
      bar.innerHTML = percentComplete + ' % complete';
    } else {
      debug('unable to compute');
    }
  }
  
   //Pick the next file in queue and upload it to remote server
  function uploadNext() {
    var xhr = new XMLHttpRequest();
   
    xhr.upload.addEventListener("progress", onUploadProgress, false);
    xhr.addEventListener("load", onUploadComplete, false);
    xhr.addEventListener("error", onUploadFailed, false);
    xhr.open("POST", "save-product");
    debug('uploading ' + file.name);
    xhr.send(fd);
  }
  
   function startUpload() {
    totalUploaded = filesUploaded = 0;
    uploadNext();
  }

