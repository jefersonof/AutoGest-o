# Guia Git da Equipe — Terminal e GitHub Desktop

Este guia apresenta o fluxo padrão de trabalho da equipe utilizando duas opções:

* **Terminal / Git Bash**
* **GitHub Desktop**

As duas formas produzem o mesmo resultado no Git. Cada desenvolvedor pode utilizar a opção com que se sentir mais confortável.

---

# 1. Estrutura das Branches

```text
main
  ↑
develop
  ↑
feature/... ou fix/...
```

* `main`: versão estável do projeto.
* `develop`: integração das alterações em desenvolvimento.
* `feature/...`: nova funcionalidade.
* `fix/...`: correção de algo existente.

Exemplos:

```text
feature/cadastro-fornecedor
feature/dashboard

fix/salvamento-veiculo
fix/layout-clientes
```

---

# 2. Começando uma Nova Tarefa

## Opção A — Terminal

Primeiro vá para a `develop`:

```bash
git switch develop
```

Atualize sua branch local:

```bash
git pull
```

Depois crie a branch da tarefa.

### Nova funcionalidade

```bash
git switch -c feature/nome-da-feature
```

### Correção

```bash
git switch -c fix/nome-da-correcao
```

Confira:

```bash
git status
```

ou:

```bash
git branch
```

O `*` indica a branch atual.

---

## Opção B — GitHub Desktop

1. Abra o projeto no GitHub Desktop.
2. Clique em **Current Branch**.
3. Selecione `develop`.
4. Clique em **Fetch origin**.
5. Se houver alterações remotas, clique em **Pull origin**.
6. Clique novamente em **Current Branch**.
7. Clique em **New Branch**.
8. Digite o nome da branch.

Para funcionalidade:

```text
feature/nome-da-feature
```

Para correção:

```text
fix/nome-da-correcao
```

9. Clique em **Create Branch**.

Agora faça as alterações normalmente no código.

---

# 3. Conferindo as Alterações

## Terminal

```bash
git status
```

O Git mostrará os arquivos modificados, criados ou removidos.

---

## GitHub Desktop

Os arquivos modificados aparecem automaticamente na área:

```text
Changes
```

É possível marcar ou desmarcar quais arquivos entrarão no commit.

---

# 4. Preparando o Commit

## Terminal

Adicionar todas as alterações:

```bash
git add .
```

Confira:

```bash
git status
```

Os arquivos preparados deverão aparecer em:

```text
Changes to be committed
```

---

## GitHub Desktop

Os arquivos alterados aparecem na área **Changes**.

Deixe marcados somente os arquivos que devem fazer parte daquele commit.

---

# 5. Criando o Commit

Utilizamos mensagens objetivas.

### Nova funcionalidade

```text
feat: adiciona cadastro de fornecedores
```

### Correção

```text
fix: corrige salvamento de veículo
```

### Documentação

```text
docs: atualiza guia Git da equipe
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

## Terminal

Exemplo:

```bash
git commit -m "fix: corrige salvamento de veículo"
```

---

## GitHub Desktop

No canto inferior esquerdo:

1. Digite a mensagem em **Summary**.
2. Clique em:

```text
Commit to nome-da-branch
```

---

# 6. Enviando a Branch para o GitHub

## Terminal

No primeiro push:

```bash
git push -u origin nome-da-branch
```

Exemplo:

```bash
git push -u origin fix/layout-clientes
```

O `-u` cria o vínculo entre a branch local e a branch remota.

Depois disso, novos envios nessa mesma branch podem ser feitos simplesmente com:

```bash
git push
```

---

## GitHub Desktop

No primeiro envio clique em:

```text
Publish branch
```

Depois do primeiro envio, novos commits podem ser enviados utilizando:

```text
Push origin
```

---

# 7. Criando o Pull Request

O Pull Request deve seguir:

```text
feature/... ou fix/...
          ↓
      Pull Request
          ↓
        develop
```

## Opção A — Terminal com GitHub CLI

Primeiro, quando necessário, confira se o GitHub CLI está autenticado:

```bash
gh auth status
```

Para criar o Pull Request, utilizamos o comando **completo e sem interação**.

### Exemplo com Fix

```bash
gh pr create --base develop --head fix/layout-clientes --title "fix: ajusta layout da tela de clientes" --body "Corrige o layout e o comportamento da tela de clientes."
```

### Exemplo com Feature

```bash
gh pr create --base develop --head feature/cadastro-fornecedor --title "feat: adiciona cadastro de fornecedores" --body "Adiciona a funcionalidade de cadastro de fornecedores ao sistema."
```

### Exemplo com documentação

```bash
gh pr create --base develop --head feature/documentacao-git --title "docs: adiciona guia de fluxo Git da equipe" --body "Adiciona o guia completo de Git da equipe, com fluxo pelo terminal e GitHub Desktop, incluindo branches feature e fix, commits, push, Pull Request, merge, stash e exclusao de branches."
```

Neste comando:

```text
--base     branch que receberá a alteração
--head     branch onde o trabalho foi realizado
--title    título do Pull Request
--body     descrição do Pull Request
```

Portanto:

```text
--base develop
```

significa:

> O Pull Request será enviado para a `develop`.

E:

```text
--head fix/layout-clientes
```

significa:

> As alterações virão da `fix/layout-clientes`.

Visualizar o Pull Request:

```bash
gh pr view
```

Listar Pull Requests:

```bash
gh pr list
```

---

## Opção B — GitHub Desktop

Depois do push:

1. Clique em **Create Pull Request**.
2. O navegador abrirá o GitHub.
3. Confira:

```text
base: develop
compare: sua-branch
```

4. Preencha título e descrição.
5. Clique em:

```text
Create pull request
```

---

# 8. Fazendo o Merge

Antes do merge, confirme que:

* o Pull Request está correto;
* não existem conflitos;
* as revisões necessárias foram concluídas;
* os testes necessários passaram.

## Terminal

Para visualizar o PR antes do merge:

```bash
gh pr view
```

Para fazer o merge **sem interação** e apagar a branch remota:

```bash
gh pr merge nome-da-branch --merge --delete-branch
```

### Exemplo com Fix

```bash
gh pr merge fix/layout-clientes --merge --delete-branch
```

### Exemplo com Feature

```bash
gh pr merge feature/cadastro-fornecedor --merge --delete-branch
```

### Exemplo com documentação

```bash
gh pr merge feature/documentacao-git --merge --delete-branch
```

Esse comando:

```text
--merge
```

faz o merge do Pull Request.

E:

```text
--delete-branch
```

remove a branch remota depois do merge.

---

## Opção B — GitHub / GitHub Desktop

Na página do Pull Request:

```text
Merge pull request
```

Depois:

```text
Confirm merge
```

Após o merge, utilize:

```text
Delete branch
```

para remover a branch remota caso ela não seja removida automaticamente.

---

# 9. Atualizando a Develop Depois do Merge

Depois do merge feito no GitHub, precisamos atualizar nossa `develop` local.

## Terminal

```bash
git switch develop
git pull
```

Agora a `develop` local possui a alteração que foi integrada pelo Pull Request.

---

## GitHub Desktop

1. Clique em **Current Branch**.
2. Selecione `develop`.
3. Clique em:

```text
Fetch origin
```

4. Se houver alterações:

```text
Pull origin
```

---

# 10. Apagando a Branch Finalizada

Se utilizamos:

```bash
gh pr merge nome-da-branch --merge --delete-branch
```

a branch remota já deverá ter sido removida.

Ainda precisamos verificar/remover a branch local.

## Terminal

Depois de estar na `develop` atualizada:

```bash
git branch -d nome-da-branch
```

Exemplo:

```bash
git branch -d fix/layout-clientes
```

Depois limpe referências remotas antigas:

```bash
git fetch --prune
```

### Fluxo após o merge

```bash
git switch develop
git pull
git branch -d nome-da-branch
git fetch --prune
```

### Caso a branch remota ainda exista

Se ela não tiver sido removida pelo GitHub CLI:

```bash
git push origin --delete nome-da-branch
```

---

## GitHub Desktop

Depois de mudar para `develop`:

1. Vá em **Branch**.
2. Escolha **Delete**.
3. Selecione a branch finalizada.

Se a branch remota ainda existir, ela também pode ser removida pelo GitHub utilizando:

```text
Delete branch
```

---

# 11. Esqueci de Criar a Feature ou Fix

Imagine que você começou a programar diretamente na `develop`.

Não faça commit nela.

## Opção A — Terminal

Primeiro confira:

```bash
git status
```

Guarde as alterações, incluindo possíveis arquivos novos:

```bash
git stash -u
```

Agora a `develop` deve estar limpa.

Atualize:

```bash
git pull
```

Crie a branch correta.

### Correção

```bash
git switch -c fix/nome
```

### Funcionalidade

```bash
git switch -c feature/nome
```

Recupere suas alterações:

```bash
git stash pop
```

Confira:

```bash
git status
```

Agora suas alterações estarão na branch correta.

Depois continue normalmente:

```bash
git add .
git commit -m "fix: descrição da correção"
git push -u origin fix/nome
```

Depois crie o Pull Request utilizando o comando completo:

```bash
gh pr create --base develop --head fix/nome --title "fix: descrição da correção" --body "Descrição das alterações realizadas."
```

---

## Opção B — GitHub Desktop

Se você alterou arquivos na branch errada:

1. Não faça commit.
2. Clique em **Current Branch**.
3. Clique em **New Branch**.
4. Crie a `feature/...` ou `fix/...`.
5. Quando o GitHub Desktop perguntar como tratar as alterações existentes, mantenha as alterações na nova branch.
6. Confira a área **Changes**.
7. Faça o commit somente na branch correta.
8. Publique a branch.
9. Crie o Pull Request.

Se houver qualquer mensagem inesperada, não descarte as alterações antes de confirmar onde elas estão.

---

# 12. Branches Locais e Remotas

## Terminal

### Locais

```bash
git branch
```

### Remotas

```bash
git branch -r
```

### Todas

```bash
git branch -a
```

### Mais detalhes

```bash
git branch -vv
```

Uma branch como:

```text
fix/layout-clientes
```

é local.

Uma referência como:

```text
origin/fix/layout-clientes
```

representa a branch remota.

---

# 13. Comandos Rápidos

### Ver situação atual

```bash
git status
```

### Branches

```bash
git branch
git branch -r
git branch -a
git branch -vv
```

### Atualizar a develop

```bash
git switch develop
git pull
```

### Criar branch

```bash
git switch -c feature/nome
```

ou:

```bash
git switch -c fix/nome
```

### Preparar e criar commit

```bash
git add .
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

### Criar Pull Request sem interação

```bash
gh pr create --base develop --head nome-da-branch --title "titulo do Pull Request" --body "Descricao das alteracoes realizadas."
```

### Visualizar Pull Request

```bash
gh pr view
```

### Listar Pull Requests

```bash
gh pr list
```

### Fazer merge e apagar branch remota

```bash
gh pr merge nome-da-branch --merge --delete-branch
```

### Apagar branch local

```bash
git branch -d nome-da-branch
```

### Apagar branch remota manualmente

```bash
git push origin --delete nome-da-branch
```

### Limpar referências antigas

```bash
git fetch --prune
```

### Guardar alterações

```bash
git stash -u
```

### Ver stashes

```bash
git stash list
```

### Recuperar alterações

```bash
git stash pop
```

---

# 14. Fluxo Diário Resumido

## Opção A — Terminal

### 1. Atualizar a develop

```bash
git switch develop
git pull
```

### 2. Criar a branch

Para uma feature:

```bash
git switch -c feature/nome
```

ou, para uma correção:

```bash
git switch -c fix/nome
```

### 3. Trabalhar no código

Faça as alterações necessárias.

Depois confira:

```bash
git status
```

### 4. Commit

```bash
git add .
git commit -m "feat: descrição da funcionalidade"
```

ou:

```bash
git add .
git commit -m "fix: descrição da correção"
```

### 5. Push

```bash
git push -u origin nome-da-branch
```

### 6. Pull Request sem interação

```bash
gh pr create --base develop --head nome-da-branch --title "titulo do Pull Request" --body "Descricao das alteracoes realizadas."
```

### 7. Conferir o Pull Request

```bash
gh pr view
```

### 8. Merge e exclusão da branch remota

```bash
gh pr merge nome-da-branch --merge --delete-branch
```

### 9. Atualizar a develop local

```bash
git switch develop
git pull
```

### 10. Apagar a branch local

```bash
git branch -d nome-da-branch
```

### 11. Limpar referências antigas

```bash
git fetch --prune
```

O fluxo completo fica:

```text
develop
   ↓
git pull
   ↓
feature/... ou fix/...
   ↓
alterar código
   ↓
git add .
   ↓
git commit
   ↓
git push
   ↓
gh pr create
   ↓
gh pr view
   ↓
gh pr merge --merge --delete-branch
   ↓
develop
   ↓
git pull
   ↓
git branch -d
   ↓
git fetch --prune
```

---

## Opção B — GitHub Desktop

```text
develop
↓
Fetch / Pull
↓
New Branch
↓
feature/... ou fix/...
↓
alterar código
↓
Changes
↓
Commit
↓
Publish Branch
↓
Create Pull Request
↓
revisão
↓
Merge
↓
Delete Branch
↓
voltar para develop
↓
Fetch / Pull
↓
apagar branch local
```

---

# 15. Exemplo Completo pelo Terminal

Vamos imaginar uma correção no layout de clientes.

### Atualizar

```bash
git switch develop
git pull
```

### Criar a fix

```bash
git switch -c fix/layout-clientes
```

Faça as alterações.

### Conferir e criar commit

```bash
git status
git add .
git commit -m "fix: ajusta layout da tela de clientes"
```

### Enviar

```bash
git push -u origin fix/layout-clientes
```

### Criar Pull Request sem interação

```bash
gh pr create --base develop --head fix/layout-clientes --title "fix: ajusta layout da tela de clientes" --body "Corrige o layout e o comportamento da tela de clientes."
```

### Conferir

```bash
gh pr view
```

### Fazer merge e apagar a branch remota

```bash
gh pr merge fix/layout-clientes --merge --delete-branch
```

### Atualizar a develop

```bash
git switch develop
git pull
```

### Apagar a branch local

```bash
git branch -d fix/layout-clientes
```

### Limpar referências

```bash
git fetch --prune
```

Pronto.

A correção passou pelo fluxo:

```text
fix/layout-clientes
        ↓
      commit
        ↓
       push
        ↓
 Pull Request
        ↓
      develop
        ↓
       merge
        ↓
branch removida
```

---

# Regra de Ouro

Antes de começar a alterar o código:

### Nova funcionalidade

```bash
git switch develop
git pull
git switch -c feature/nome
```

### Correção

```bash
git switch develop
git pull
git switch -c fix/nome
```

Nunca desenvolva diretamente na `main`.

Evite também desenvolver diretamente na `develop`.

Cada tarefa deve ter sua própria branch.

Se perceber que começou a trabalhar na `develop` por engano e ainda não fez commit:

```bash
git stash -u
git pull
git switch -c fix/nome
git stash pop
```

ou crie uma `feature/...`, dependendo do tipo de trabalho.

Assim as branches principais permanecem organizadas e cada alteração passa pelo fluxo de revisão antes de ser integrada.
