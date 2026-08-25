# Guia Git da Equipe — AutoGestão Pro

Este documento define o fluxo Git utilizado pela equipe no desenvolvimento do **AutoGestão Pro**.

O objetivo é manter o código organizado, evitar alterações diretamente nas branches principais e facilitar o trabalho simultâneo de vários desenvolvedores.

---

# 1. Estrutura das Branches

Utilizamos principalmente:

```text
main
  ↑
develop
  ↑
feature/... ou fix/...
```

## `main`

Contém versões estáveis do sistema.

Evite desenvolver diretamente nela.

## `develop`

É a branch principal de desenvolvimento.

As funcionalidades e correções são integradas nela antes de chegarem à `main`.

Evite alterar arquivos diretamente na `develop`.

## `feature/`

Utilizada para desenvolver **funcionalidades novas**.

Exemplos:

```text
feature/cadastro-fornecedor
feature/ordem-servico
feature/dashboard
feature/controle-estoque
```

## `fix/`

Utilizada para **corrigir algo que já existe**.

Exemplos:

```text
fix/layout-clientes
fix/salvamento-veiculo
fix/validacao-cpf
fix/tipo-dado-ano-veiculo
```

Regra simples:

```text
Funcionalidade nova → feature/
Correção de problema → fix/
```

---

# 2. Antes de Começar Qualquer Tarefa

Antes de alterar qualquer arquivo, vá para a `develop`:

```bash
git switch develop
```

Depois atualize sua `develop` local:

```bash
git pull
```

Isso garante que sua nova branch será criada a partir da versão mais recente do projeto.

O início de uma tarefa normalmente será:

```bash
git switch develop
git pull
```

Depois criamos a branch da tarefa.

---

# 3. Criando uma Feature

Se você vai desenvolver algo novo:

```bash
git switch -c feature/nome-da-feature
```

Exemplo:

```bash
git switch -c feature/cadastro-fornecedor
```

O parâmetro `-c` significa:

> criar uma nova branch e já mudar para ela.

Confira:

```bash
git branch
```

Exemplo:

```text
  develop
* feature/cadastro-fornecedor
  main
```

O `*` indica a branch em que você está trabalhando.

---

# 4. Criando uma Fix

Se você vai corrigir algo existente:

```bash
git switch -c fix/nome-da-correcao
```

Exemplo:

```bash
git switch -c fix/layout-clientes
```

Confira:

```bash
git branch
```

Exemplo:

```text
  develop
* fix/layout-clientes
  main
```

Agora faça as alterações necessárias no código.

---

# 5. Verificando as Alterações

Durante o desenvolvimento utilize:

```bash
git status
```

Exemplo:

```text
Changes not staged for commit:

    modified: ClienteService.java
    modified: clientes.html
    modified: app.css
```

O `git status` é um dos comandos mais importantes do Git.

Use sempre que tiver dúvida sobre o estado atual do projeto.

---

# 6. Preparando o Commit

Depois de terminar uma etapa do trabalho, confira:

```bash
git status
```

Para adicionar todas as alterações:

```bash
git add .
```

Depois confira novamente:

```bash
git status
```

Os arquivos deverão aparecer como preparados para commit.

---

# 7. Criando o Commit

Para uma funcionalidade:

```bash
git commit -m "feat: adiciona cadastro de fornecedores"
```

Para uma correção:

```bash
git commit -m "fix: corrige salvamento de veículo"
```

Outros prefixos úteis:

```text
feat:      nova funcionalidade
fix:       correção
docs:      documentação
style:     alterações visuais ou formatação
refactor:  reorganização do código
test:      criação ou alteração de testes
```

Evite mensagens vagas como:

```text
alterações
teste
ajustes
commit novo
mudanças
```

Prefira:

```text
fix: corrige validação da placa

feat: adiciona cadastro de fornecedores

docs: adiciona guia Git da equipe
```

---

# 8. Enviando a Branch para o GitHub

Na primeira vez que enviar uma branch:

```bash
git push -u origin nome-da-branch
```

Exemplo:

```bash
git push -u origin fix/layout-clientes
```

O `-u` cria o vínculo entre:

```text
branch local
     ↕
branch remota
```

Depois disso, enquanto estiver nessa branch, normalmente basta:

```bash
git push
```

---

# 9. Abrindo o Pull Request

Depois do `push`, abra o repositório no GitHub.

O GitHub normalmente exibirá:

```text
Compare & pull request
```

Clique nessa opção.

Confira atentamente:

```text
base: develop
compare: fix/layout-clientes
```

ou:

```text
base: develop
compare: feature/cadastro-fornecedor
```

A ideia é:

```text
feature/... ──┐
              ├── Pull Request ──→ develop
fix/... ──────┘
```

Não envie diretamente para `main`, salvo quando esse for explicitamente o processo definido pela equipe.

---

# 10. Descrição do Pull Request

O Pull Request deve explicar claramente o que foi feito.

Exemplo:

```text
Título:

fix: ajusta layout da tela de clientes


Alterações:

- corrige espaçamento dos campos;
- ajusta comportamento em telas menores;
- corrige alinhamento dos botões.


Testes:

- cadastro de cliente testado;
- edição testada;
- layout testado em diferentes tamanhos de tela.
```

Depois:

```text
Create pull request
```

Aguarde a revisão da equipe quando houver revisão obrigatória.

---

# 11. Merge do Pull Request

Depois que a alteração estiver revisada e testada:

```text
Merge pull request
```

Depois:

```text
Confirm merge
```

O resultado será:

```text
fix/layout-clientes
        │
        │ Pull Request
        ▼
     develop
```

ou:

```text
feature/cadastro-fornecedor
        │
        │ Pull Request
        ▼
     develop
```

A equipe também pode optar por:

```text
Squash and merge
```

ou:

```text
Rebase and merge
```

Siga sempre o padrão definido para o projeto.

---

# 12. Atualizando a Develop Depois do Merge

O merge aconteceu no GitHub.

Sua `develop` local ainda pode estar desatualizada.

Volte para ela:

```bash
git switch develop
```

Depois:

```bash
git pull
```

Agora sua `develop` local contém a alteração que acabou de ser integrada.

---

# 13. Apagando a Branch Finalizada

Depois que o Pull Request foi integrado, a branch da tarefa normalmente não é mais necessária.

## Apagar localmente

```bash
git branch -d nome-da-branch
```

Exemplo:

```bash
git branch -d fix/layout-clientes
```

O `-d` é a opção segura.

Evite utilizar `-D` sem saber exatamente por que o Git está impedindo a exclusão.

## Apagar no remoto

```bash
git push origin --delete nome-da-branch
```

Exemplo:

```bash
git push origin --delete fix/layout-clientes
```

Depois:

```bash
git fetch --prune
```

O `--prune` remove referências locais de branches remotas que já não existem.

---

# 14. Esqueci de Criar a Branch

Este é um erro comum.

Você estava na:

```text
develop
```

e começou a alterar arquivos.

Ainda **não fez commit**.

Primeiro:

```bash
git status
```

Se existirem alterações, podemos guardá-las temporariamente.

---

# 15. Guardando as Alterações com Stash

Use:

```bash
git stash -u
```

O `stash` funciona como uma **gaveta temporária**.

O `-u` também inclui arquivos novos ainda não rastreados.

Confira:

```bash
git status
```

O esperado:

```text
nothing to commit, working tree clean
```

Suas alterações não foram perdidas.

Temos:

```text
develop
   │
   └── limpa


stash
   │
   └── alterações guardadas
```

---

# 16. Atualizando a Develop

Agora:

```bash
git pull
```

Assim você recebe possíveis alterações feitas pelos outros integrantes da equipe.

---

# 17. Criando a Branch Correta

Se era uma correção:

```bash
git switch -c fix/nome-da-correcao
```

Se era uma funcionalidade:

```bash
git switch -c feature/nome-da-feature
```

Exemplo:

```bash
git switch -c fix/layout-clientes
```

---

# 18. Recuperando as Alterações

Agora:

```bash
git stash pop
```

Confira:

```bash
git status
```

Suas alterações deverão aparecer novamente.

Mas agora estarão na branch correta:

```text
develop
   │
   └── limpa


fix/layout-clientes
   │
   ├── arquivo alterado
   ├── arquivo alterado
   └── arquivo novo
```

Continue normalmente:

```bash
git add .

git commit -m "fix: ajusta layout da tela de clientes"

git push -u origin fix/layout-clientes
```

Depois abra o Pull Request para:

```text
fix/layout-clientes → develop
```

---

# 19. Resumo — Esqueci de Criar a Branch

Se ainda **não fez commit**:

```bash
git status

git stash -u

git pull

git switch -c fix/nome-da-correcao

git stash pop
```

Depois:

```bash
git add .

git commit -m "fix: descrição da correção"

git push -u origin fix/nome-da-correcao
```

Depois:

```text
Pull Request
      ↓
develop
      ↓
Merge
```

Por fim:

```bash
git switch develop

git pull

git branch -d fix/nome-da-correcao

git push origin --delete fix/nome-da-correcao

git fetch --prune
```

---

# 20. `git stash pop` x `git stash apply`

## `git stash pop`

```bash
git stash pop
```

Recupera as alterações e, quando aplicado normalmente, remove aquele stash da lista.

## `git stash apply`

```bash
git stash apply
```

Recupera as alterações, mas mantém o stash.

Depois de confirmar que está tudo certo:

```bash
git stash drop
```

Para visualizar os stashes existentes:

```bash
git stash list
```

---

# 21. Conflito no Stash

Se depois de:

```bash
git stash pop
```

aparecer:

```text
CONFLICT
```

não execute outro `stash pop`.

Primeiro:

```bash
git status
```

Veja quais arquivos estão em conflito.

Resolva os conflitos nos arquivos.

Depois:

```bash
git add .
```

E continue o processo normalmente.

---

# 22. E se Eu Já Fiz Commit na Branch Errada?

Se você já fez um commit diretamente na `develop` ou `main`, **não utilize este procedimento automaticamente**.

Primeiro confira:

```bash
git status
```

e:

```bash
git log --oneline -5
```

Dependendo da situação, poderá ser necessário utilizar:

```text
reset
cherry-pick
revert
```

Principalmente se o commit já tiver sido enviado ao GitHub.

Não utilize `reset --hard` ou force um `push` em uma branch compartilhada sem entender o impacto para os demais desenvolvedores.

---

# 23. Branches Locais e Remotas

Ver branches locais:

```bash
git branch
```

Ver branches remotas:

```bash
git branch -r
```

Ver todas:

```bash
git branch -a
```

Ver branches e seus vínculos:

```bash
git branch -vv
```

Exemplo:

```text
develop                     [origin/develop]
feature/cadastro-fornecedor [origin/feature/cadastro-fornecedor]
main                        [origin/main]
```

`origin/...` representa a referência ao repositório remoto.

---

# 24. O que é HEAD?

Ao executar:

```bash
git branch
```

pode aparecer:

```text
* develop
  main
```

O `*` indica a branch atual.

O Git utiliza uma referência chamada:

```text
HEAD
```

para representar onde você está atualmente.

Podemos imaginar:

```text
HEAD
 │
 ▼
develop
```

No remoto pode aparecer:

```text
origin/HEAD -> origin/main
```

Isso indica que a branch padrão do repositório remoto é a `main`.

---

# 25. Comandos Rápidos

### Situação atual

```bash
git status
```

### Branch atual

```bash
git branch
```

### Branches remotas

```bash
git branch -r
```

### Todas as branches

```bash
git branch -a
```

### Trocar de branch

```bash
git switch nome-da-branch
```

### Criar branch

```bash
git switch -c nome-da-branch
```

### Atualizar

```bash
git pull
```

### Preparar alterações

```bash
git add .
```

### Commit

```bash
git commit -m "mensagem"
```

### Primeiro push

```bash
git push -u origin nome-da-branch
```

### Próximos pushes

```bash
git push
```

### Apagar branch local

```bash
git branch -d nome-da-branch
```

### Apagar branch remota

```bash
git push origin --delete nome-da-branch
```

### Limpar referências antigas

```bash
git fetch --prune
```

### Guardar alterações temporariamente

```bash
git stash -u
```

### Recuperar alterações

```bash
git stash pop
```

### Ver stashes

```bash
git stash list
```

---

# 26. Fluxo Diário da Equipe

Este é o fluxo que deve ser utilizado na maioria das tarefas.

## Começar

```bash
git switch develop

git pull
```

## Criar a branch

Funcionalidade:

```bash
git switch -c feature/nome-da-feature
```

Correção:

```bash
git switch -c fix/nome-da-correcao
```

## Trabalhar

Faça as alterações necessárias.

Confira:

```bash
git status
```

## Salvar no Git

```bash
git add .

git commit -m "feat: descrição da funcionalidade"
```

ou:

```bash
git commit -m "fix: descrição da correção"
```

## Enviar

```bash
git push -u origin nome-da-branch
```

## GitHub

```text
Abrir Pull Request
       ↓
base: develop
       ↓
Revisar
       ↓
Merge
```

## Atualizar o computador

```bash
git switch develop

git pull
```

## Limpar a branch finalizada

```bash
git branch -d nome-da-branch

git push origin --delete nome-da-branch

git fetch --prune
```

---

# Regra de Ouro

Antes de começar a programar:

```bash
git switch develop
git pull
git switch -c feature/nome-da-feature
```

ou:

```bash
git switch develop
git pull
git switch -c fix/nome-da-correcao
```

**Só depois altere o código.**

Se esquecer:

```bash
git stash -u
git pull
git switch -c fix/nome
git stash pop
```

Assim mantemos `main` e `develop` organizadas e cada alteração fica isolada em sua própria branch.
