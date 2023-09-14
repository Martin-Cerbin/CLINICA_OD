window.addEventListener('load', function () {

    (function(){

      const url = '/pacientes';
      const settings = {method: 'GET'}

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección del JSON
         for(paciente of data){
            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("pacientesTable");
            var pacienteRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
           pacienteRow.id = tr_id;

            //por cada pelicula creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar una pelicula
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                      ' type="button" oneclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar la pelicula que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                      paciente.id +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            pacienteRow.innerHTML = '<td>' + paciente.id + '</td>' +
                    '<td class=\"td_titulo\">' + paciente.nombre.toUpperCase() + '</td>' +
                     '<td class=\"td_titulo\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_categoria\">' + paciente.cedula.toUpperCase() + '</td>' +
                    '<td class=\"td_categoria\">' + paciente.fechaIngreso + '</td>' +
                    '<td class=\"td_categoria\">' + paciente.email.toUpperCase() + '</td>' +
                     '<td>' + updateButton + '</td>'+
                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_all_pacientes.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })