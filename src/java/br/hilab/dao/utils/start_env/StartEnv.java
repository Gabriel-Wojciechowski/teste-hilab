
package br.hilab.dao.utils.start_env;

import br.hilab.dao.utils.start_env.data.CreateCategoria;
import br.hilab.dao.utils.start_env.data.CreateTables;

public class StartEnv {
    public static void main(String[] args) {
        CreateTables.main(args);
        CreateCategoria.main(args);
    }
}
