package sia.tacocloud.web.controller.tacos.data;

import sia.tacocloud.web.controller.tacos.Taco;

import java.security.cert.CertificateException;

public interface TacoRepository {
    Taco save(Taco design);
}
