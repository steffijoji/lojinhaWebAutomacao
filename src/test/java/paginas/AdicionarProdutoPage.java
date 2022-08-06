package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdicionarProdutoPage {
    private WebDriver navegador;

    public AdicionarProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public AdicionarProdutoPage dadosDoProduto(String nome, String valor, String cor) {
        navegador.findElement(By.id("produtonome")).sendKeys(nome);
        navegador.findElement(By.id("produtovalor")).sendKeys(valor);
        navegador.findElement((By.id("produtocores"))).sendKeys(cor);

        return this;
    }

    public ListaDeProdutosPage salvarProdutoErro() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage(navegador);
    }

    public EditarProdutoPage salvarProdutoPermitido() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new EditarProdutoPage(navegador);
    }

}
