# Marvel Android App 🚀

Bem-vindo ao **Marvel Android App**! Este é um aplicativo desenvolvido em Kotlin para Android, consumindo a API da Marvel para exibir personagens, detalhes e gerenciamento de favoritos. O projeto foi criado com foco em boas práticas, arquitetura MVVM e experiência do usuário aprimorada.

---

## 📌 Tecnologias Utilizadas

- **Linguagem:** Kotlin
- **Arquitetura:** MVVM (Model-View-ViewModel)
- **Injeção de Dependência:** Koin
- **Requisições HTTP:** Retrofit
- **Manipulação de Dados:** Coroutines + Flow
- **Banco de Dados Local:** Room
- **Componentes de UI:** RecyclerView, ConstraintLayout, Fragment, ViewBinding
- **Gerenciamento de Estados:** LiveData + StateFlow
- **Navegação:** FragmentManager
- **Controle de Versões:** Git & GitHub

---

## 🔥 Funcionalidades

✅ Listagem de personagens da Marvel com informações detalhadas
✅ Pesquisa e filtro de personagens
✅ Tela de detalhes dos personagens
✅ Sistema de favoritos salvo no banco de dados local (Room)
✅ Interface fluida e responsiva
✅ Barra de carregamento (ProgressBar) durante requisições
✅ Tratamento de erros e exibição de mensagens para o usuário
✅ Navegação entre telas de forma dinâmica

---

## 🎯 Como Executar o Projeto

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/seu-usuario/Marvel-Android.git
   ```
2. **Abra o projeto no Android Studio**
3. **Configure a chave da API Marvel:**
   - Crie uma conta em [Marvel Developer](https://developer.marvel.com/)
   - Gere sua chave pública e privada
   - Adicione as credenciais no arquivo `local.properties`:
     ```properties
     MARVEL_PUBLIC_KEY=your_public_key
     MARVEL_PRIVATE_KEY=your_private_key
     ```
4. **Compile e execute no emulador ou dispositivo físico!** 🚀

---

## 📸 Capturas de Tela

### 🏠 Tela Principal
![Home Screen](https://via.placeholder.com/600x300?text=Home+Screen)

### ⭐ Tela de Favoritos
![Favorites Screen](https://via.placeholder.com/600x300?text=Favorites+Screen)

### 🔍 Tela de Detalhes
![Details Screen](https://via.placeholder.com/600x300?text=Details+Screen)

---

## 🛠 Melhorias Futuras

🔹 Implementação de paginação para melhor performance ⚡
🔹 Modo offline com cache de dados 📡
🔹 Melhorias na interface e experiência do usuário 🖌️
🔹 Testes unitários e instrumentados 🧪

---

## 🤝 Contribuições

Ficaremos felizes em receber contribuições! Para contribuir:
1. Faça um **fork** do projeto 🍴
2. Crie uma **branch** com a feature ou correção (`git checkout -b minha-feature`)
3. Faça o **commit** das alterações (`git commit -m 'Minha nova feature'`)
4. Faça o **push** (`git push origin minha-feature`)
5. Abra um **Pull Request** 🚀

---

## 📜 Licença

Este projeto é licenciado sob a **MIT License** - sinta-se livre para utilizá-lo e modificá-lo conforme necessário.

---

Feito com 💙 por [Lucas Santos](https://github.com/lfmdsant)
