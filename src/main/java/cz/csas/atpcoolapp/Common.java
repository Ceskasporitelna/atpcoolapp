package cz.csas.atpcoolapp;

import cz.csas.atpcoolapp.entity.BillIItem;
import cz.csas.atpcoolapp.entity.TicketItem;

/**
 * Created by pavel on 15.09.2017.
 *
 */
public class Common {
    public static final String POOL_DATA_SOURCE = "java:/comp/env/jdbc/bigsix";
    // public static final String CONTEXT_FACTORY = "weblogic.jndi.WLInitialContextFactory";

    public BillIItem PIVO = new  BillIItem(null, null,"0213101", "a0a0a0a0a0a0", "Pilsner Urqell, světlý ležák, plech 0,5l");
    public BillIItem MLEKO = new  BillIItem(null, null,"0114301", "a0a0a0a0a0a0", "Tatra Trvanlivé polotučné mléko 1,5%, 1l");
    public BillIItem VINO = new  BillIItem(null, null,"0212105", "a0a0a0a0a0a0", "Moët & Chandon Imperial Brut, 0,75l");
    public BillIItem VEJCE = new  BillIItem(null, null,"2000005", "a0a0a0a0a0a0", "Vejce M balení, 10ks");

    // public TicketItem PIVO = new TicketItem("213101", "Pilsner Urqell, světlý ležák, plech 0,5l", "a0a0a0a0a0a0");
    // public TicketItem MLEKO = new TicketItem("114301", "Tatra Trvanlivé polotučné mléko 1,5%, 1l", "a0a0a0a0a0a0");
    // public TicketItem VINO = new TicketItem("212105", "Moët & Chandon Imperial Brut, 0,75l", "a0a0a0a0a0a0");
    // public TicketItem VEJCE = new TicketItem("2000005", "Vejce M balení, 10ks", "a0a0a0a0a0a0");

    public static final String DEMO_AES_PRIV = "{\n" +
            "  \t\"method\":\"AES\",\n" +
            "  \t\"password\":\"0123456789ABCDEF\",\n" +
            "  \t\"salt\":\"0123456789ABCDEF\",\n" +
            "  \t\"keyLength\":128\n" +
            "  }";

    public static final String DEMO_PAILLIER_PRIV = "{\n" +
            "  \t\"method\":\"PAILLIER\",\n" +
            "    \"preCalculatedDenominator\": \"12106853997386792569330058721713456303240354594631911447132191952767560013759\",\n" +
            "    \"lambda\": \"8730610850576098108475732642812548398736937109943727651665665679037686442986\"\n" +
            "  }";
    public static final String DEMO_PAILLIER_PUB = "{\n" +
            "\"method\":\"PAILLIER\",\n" +
            "    \"nSquared\": \"2744048369671095574561583007959980901355918163690500471631736129549114696724932097880235179184429799730201850640419024858965622819166137651676944593730281\",\n" +
            "    \"g\": \"9819266694848734599019384375903625362875798967138880476824318058493575873131\",\n" +
            "    \"bits\": 256,\n" +
            "    \"n\": \"52383665103456588650854395856875290392884554625553083201198459773567331028909\"\n" +
            "  }";
}
