document.getElementById('cep')?.addEventListener('blur',async function(){
  const cep=this.value.replace(/\D/g,''); if(cep.length!==8)return;
  try{const resposta=await fetch(`https://viacep.com.br/ws/${cep}/json/`);const d=await resposta.json();
    if(d.erro){alert('CEP não encontrado.');return;}
    document.getElementById('rua').value=d.logradouro||'';document.getElementById('bairro').value=d.bairro||'';
    document.getElementById('cidade').value=d.localidade||'';document.getElementById('estado').value=d.uf||'';
  }catch(e){alert('Não foi possível consultar o CEP.');}
});
