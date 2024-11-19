
# RPG Character Creation App

Este é um aplicativo Android para a criação de personagens de RPG, especificamente para o universo de **Ordem Paranormal**. O aplicativo permite que os jogadores criem e personalizem seus personagens com base em atributos, classes, origens e habilidades.

Alunos responsáveis:
Alfeson Luiz Araujo de Macedo - 1813166
Fabio Luis Amaro de Lima Silva Santana - 1654284
Giovanna de Oliveira Ardesore - 6694107
João Alexandre Silva de Santana - 1841102
Leticia Alves de Sena - 1701283

## Sumário

- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Instalação](#instalação)
- [Dependências](#dependências)
- [Arquitetura](#arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

## Visão Geral

O **RPG Character Creation App** oferece uma interface intuitiva para a criação de personagens com atributos, classes, e origens detalhados. Ele é especialmente projetado para jogos no universo de **Ordem Paranormal**, permitindo que os jogadores customizem facilmente seus personagens antes das sessões de jogo.

## Funcionalidades

- Criação de personagens com atributos personalizáveis.
- Seleção de origens e classes com base no universo **Ordem Paranormal**.
- Armazenamento local dos personagens usando o banco de dados Room.
- Interface amigável, utilizando **Jetpack Compose** para o design.

## Instalação

### Pré-requisitos

- Android Studio Flamingo ou superior.
- Android SDK 24 ou superior.
- Kotlin 1.8 ou superior.

### Passos para rodar o projeto localmente

1. Clone o repositório:

   ```bash
   git clone https://github.com/usuario/rpg-character-creation-app.git
   ```

2. Abra o projeto no Android Studio.

3. Sincronize as dependências do projeto executando `Sync Project with Gradle Files`.

4. Compile e execute o aplicativo em um emulador ou dispositivo físico Android.

## Dependências

O projeto utiliza as seguintes dependências principais:

- **Room**: Para persistência de dados.
- **Jetpack Compose**: Para construção da interface de usuário.
- **AppCompat**: Para compatibilidade com versões anteriores do Android.
- **RecyclerView**: Para exibição de listas dinâmicas.

Aqui estão as principais dependências no `build.gradle.kts`:

```kotlin
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
```

## Arquitetura

O aplicativo segue a arquitetura **MVVM (Model-View-ViewModel)** para garantir a separação de responsabilidades e facilitar a escalabilidade e manutenção.

- **Model**: Contém as entidades e a camada de persistência de dados (via Room).
- **ViewModel**: Gerencia a lógica de negócios e estado do aplicativo.
- **View**: Implementada utilizando **Jetpack Compose** para renderizar a interface.

### Exemplo de Classe Principal

```kotlin
@Entity(tableName = "character")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val characterName: String,
    val playerName: String,
    val appearance: String,
    val personality: String,
    val history: String,
    val attributes: List<Attribute>,
    val origin: Origin,
    val skills: List<Skill>?,
    val characterClass: CharacterClass
)
```

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Android Jetpack**: Para a construção do app seguindo as melhores práticas do Android moderno.
  - **Room**: Para persistência de dados.
  - **Jetpack Compose**: Para construção da interface de usuário declarativa.
- **Gradle**: Sistema de automação de build.

## Contribuindo

Contribuições são bem-vindas! Se você deseja contribuir, siga os passos abaixo:

1. Fork este repositório
