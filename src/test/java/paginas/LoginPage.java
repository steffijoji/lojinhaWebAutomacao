package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//regras de um PageObject: 1) tenha um atributo da classe do tipo WebDriver. 2)tenha um construtor da classe que pegue um
//atributo (navegador) de fora e jogue para dentro do atributo da classe (navegador)

public class LoginPage {
    private WebDriver navegador;

    //construtor:
    public LoginPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public LoginPage informarUsuario(String usuario) {
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys(usuario);

        return this;
    }

    public LoginPage informarSenha(String senha) {
        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys(senha);

        return this;
    }

    public ListaDeProdutosPage entrarLogin() {
        navegador.findElement(By.name("action")).click();

        return new ListaDeProdutosPage(navegador);
    }
}
