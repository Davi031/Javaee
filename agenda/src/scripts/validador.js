/**
 * Validação de formulário
 * @author Davi Martins
 */

function validar(){
	alert("teste")
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	if (nome === ""){
		alert('Preencha o campo nome')
		frmContato.nome.focus()
		return false
	} else if (fone === ""){
		alert('Preencha o campo Fone')
		frmContato.fone.focus()
		return false
	} else {
		document.form["frmContato"].submit()
	}
	
}