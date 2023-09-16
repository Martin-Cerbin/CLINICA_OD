function deleteBy(id)
{
          //con fetch invocamos a la API de odontologo con el método DELETE
          //pasandole el id en la URL
          const url = '/turnos/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json())

          //borrar la fila de odontologo eliminada
          let row_id = "#tr_" + id;
          document.querySelector(row_id).remove();

}