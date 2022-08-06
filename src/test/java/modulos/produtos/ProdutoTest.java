package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutoTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver_win32\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Maximizar a tela
        this.navegador.manage().window().maximize();

        // Definir um tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a página da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Valor nao permitido 0,00")
    public void testValorIgualZero() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .entrarLogin()
                .clicarAdicionarProduto()
                .dadosDoProduto("Play Station 4", "000", "Preto, Branco")
                .salvarProdutoErro()
                .capturarMensagemApresentada();

       Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Valor nao permitido 7.000,01")
    public void testValorSeteMileUm() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .entrarLogin()
                .clicarAdicionarProduto()
                .dadosDoProduto("Bicicleta", "70000.1", "Azul, Vermelho") //valor com bug, deveria ser 700001
                .salvarProdutoErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Valor permitido 0,01")
    public void testValorPermitidoUmCentavo() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .entrarLogin()
                .clicarAdicionarProduto()
                .dadosDoProduto("Bala", "001", "Rosa")
                .salvarProdutoPermitido()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Valor permitido 7.000,00")
    public void testValorPermitidoSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .entrarLogin()
                .clicarAdicionarProduto()
                .dadosDoProduto("Monitor", "700000", "Preto")
                .salvarProdutoPermitido()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        // Fechar o navegador
        navegador.quit();
    }

}
