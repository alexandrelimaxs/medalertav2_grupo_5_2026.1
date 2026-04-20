# Trabalho Prático - Qualidade e Teste

O grupo deverá aplicar os conceitos aprendidos na disciplina Qualidade e Teste em até dois softwares livres à escolha.

A professora deverá ser consultada previamente para analisar o grau de dificuldade e a aderência no escopo da disciplina Qualidade e Teste.

- **Sugestão de repositórios:** [repo-software-testing-courses](https://github.com/orgs/repo-software-testing-courses/repositories)
- **Entrega no GitHub Classroom:** [Link da atividade](https://classroom.github.com/a/UHz2Yr1s) *(Entregar link da documentação editável com histórico de versão aqui na atividade)*

> **Importante:** Todos os artefatos devem estar disponíveis na branch principal (`main` ou `master`) do repositório acima. Artefatos incluídos em outras branches ou em outros repositórios não serão considerados para avaliação.
> 
> O arquivo `README.md` deve conter links diretos ou instruções claras para localizar todos os artefatos entregues (códigos, documentos, diagramas, etc.). Caso um artefato não esteja devidamente referenciado ou seja difícil de localizar, ele não será avaliado.
> 
> Os slides e material de apresentação devem ser disponibilizados no diretório apropriado enviado pela professora.

---

## 📦 Entregas

Serão realizadas duas entregas:

### 📌 Entrega 1 (Peso 3)
- **Descrição do escopo do(s) sistema(s):**
  - Definir quais módulos/componentes serão testados. Descrever o escopo no Plano de Teste.
  - Disponibilizar o código-fonte original.
- **Projetar casos de testes unitários:**
  - Pelo menos uma classe (não CRUD [entidade] e com complexidade razoável) para cada membro do grupo.
- **Plano de teste:**
  - Elaborar o documento de Plano de Teste. *Sugestão: utilizar template apresentado em aula*.
  - Incluir quais artefatos serão gerados, ferramentas que serão utilizadas, etc.
- **Projetar e executar casos de teste manuais:**
  - Pelo menos uma funcionalidade para cada membro do grupo.
  - Uso da ferramenta Testlink para ao menos um cenário de teste. O grupo pode optar por usar outra ferramenta.
  - Os demais casos podem ser feitos por ferramenta ou documentos de texto/planilha.
- **Reportar as issues:**
  - Registrar problemas encontrados durante o teste, usando uma ferramenta de bug tracker à escolha (Ex: GitHub issues, Mantis, Bugzilla, etc).

### 📌 Entrega 2 (Peso 5)
Realizar no mínimo:
- Melhorar e aumentar os casos de teste unitários, isolando suas dependências.
- Implementar casos de teste de integração.
- Indicação das medidas de cada atributo de qualidade da ISO 25010 seguindo uma escala. Justificar as decisões para indicação das medidas.
- **Implementar testes de sistema considerando requisitos funcionais:**
  - Uso de Selenium.
  - Pelo menos um requisito não funcional (atributos de qualidade - Exemplo: desempenho, segurança) *(último opcional)*.
- **Projetar e melhorar o conjunto de casos de teste, utilizando as técnicas:**
  - Funcional.
  - Estrutural (ao menos 80% de cobertura no critério todas-arestas): Pelo menos uma classe (não CRUD [entidade] e com alta complexidade) para cada membro do grupo.
  - Baseada em Defeitos (80% de escore de mutação nas mesmas classes do teste estrutural).
- **Relatório de inspeção do código-fonte (ex, usando Sonar):**
  - Executar a ferramenta e enviar print.
  - Resolver os problemas de pelo menos uma classe (não CRUD [entidade] e com complexidade razoável) para cada membro do grupo.
  - Enviar print depois das correções.

> **Nota sobre documentos de texto:** Devem ser elaborados no Google Docs, com todos os membros logados em suas contas, de forma que seja possível visualizar a colaboração individual de cada integrante. Os links para esses documentos devem estar disponíveis no arquivo `README` do repositório.

---

## 📊 Avaliação

Para solução serão avaliados:
1. O uso adequado dos conceitos de Qualidade e Teste.
2. Artefatos produzidos com base na sua completude, corretude e capacidade de argumentação em relação às decisões tomadas.
3. Resultados e colaboração individual no trabalho. Serão considerados: participação em sala no desenvolvimento do trabalho, colaboração no GitHub e descrição das responsabilidades do aluno.
4. Apresentação e corretude das respostas individuais durante as apresentações do trabalho.

---

## 📜 Regras

- O trabalho deve ser feito em grupos de **5 pessoas**. As responsabilidades de cada aluno devem ser documentadas e registradas.
- Todos os alunos devem apresentar o trabalho no prazo definido. O não cumprimento do prazo invalida o trabalho. Alunos que não participarem da apresentação ficarão sem nota do trabalho.

---

## 📅 Cronograma

| Atividade | Data |
| :--- | :--- |
| Apresentar a formação dos grupos e escolha do(s) sistema(s) adotado | 06/04/2026 |
| Criar repositório do grupo no GitHub Classroom e fazer fork do(s) sistema(s) escolhido(s) | - |
| **Entrega 1** | **27/04/2026** |
| Apresentação parcial do trabalho | 27 e 29/04/2026 |
| **Entrega 2** | **17/06/2026** |
| Apresentação final do trabalho | 15 e 17/06/2026 |

---

## 💻 Requisitos dos softwares a serem adotados

### Obrigatório
- O código-fonte precisa estar disponível.
- Ao menos um dos projetos não deve ser composto apenas por funcionalidades simples, como formulários de cadastro sem algoritmos mais elaborados. Um dos projetos deve conter classes com uma complexidade razoável, ou seja, que incluam comandos com desvios, laços, ou estruturas de controle.
- Um dos projetos deve possuir ao menos uma classe com alta complexidade para cada membro do grupo. **Mínimo: complexidade ciclomática de cada classe sob teste = 10**.

### Desejável
- Um dos projetos ser um sistema web.
- Projeto com complexidade mais alta ser desenvolvido em Java (para conseguir aplicar todas as ferramentas que serão abordadas no curso).

---

## 💡 Considerações

- Os membros da equipe serão avaliados pelo resultado final e pelos resultados individuais alcançados. Assim, numa mesma equipe, um membro pode ficar com nota 9.0 e outro com nota 5.0, por exemplo.
- Será considerado o nível de dificuldade dos projetos na composição da nota final. Importante consultar a professora para adequação.
