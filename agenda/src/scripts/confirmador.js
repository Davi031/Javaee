/**
 * confirmação de exclusão de um contato
 * @author Davi
 */

function confirmar(idcon){
	let resposta = confirm("Confirmar a exclusão deste contato?")
	if (resposta === true){
		//alert(idcon)
		window.location.href = "delete?idcon=" + idcon 
		
	}
	
}
