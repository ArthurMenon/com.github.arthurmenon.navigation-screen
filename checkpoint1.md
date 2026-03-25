Commit 1 — "Passagem de múltiplos parâmetros"
Nesse commit evoluí a navegação para suportar mais de um dado sendo enviado entre telas, o que exigiu alterações em 3 arquivos.
Na MainActivity.kt, importei o NavType e expandi a rota de "perfil/{nome}" para "perfil/{nome}/{idade}". Para que o Compose Navigation interprete cada argumento corretamente, defini os tipos explicitamente dentro de um listOf(): navArgument("nome") { type = NavType.StringType } e navArgument("idade") { type = NavType.IntType } — esse detalhe é importante porque, sem a tipagem, a navegação pode se comportar de forma inesperada com valores numéricos. A idade é recuperada com it.arguments?.getInt("idade", 0).
No MenuScreen.kt, a navegação foi atualizada para navController.navigate("perfil/Fulano de Tal/27"), já que agora a rota exige os dois valores.
Na PerfilScreen.kt, acrescentei idade: Int na assinatura e atualizei o texto para "PERFIL - $nome tem $idade anos", fazendo a tela exibir dinamicamente as duas informações recebidas.

Commit 2 — "Inserindo valor ao parâmetro opcional na tela de Pedidos"
Aqui só o MenuScreen.kt foi alterado. O objetivo era validar na prática o que havia sido configurado no commit anterior — alterei a navegação de navController.navigate("pedidos") para navController.navigate("pedidos?cliente=Cliente XPTO") para verificar se a tela de Pedidos de fato recebia e exibia o valor enviado, em vez de cair no defaultValue "Cliente Genérico".

Commit 3 — "Passagem de parâmetros opcionais na tela de Pedidos"
Aqui alterei 2 arquivos para introduzir um parâmetro que pode ou não ser enviado para a tela de Pedidos, sem que a navegação quebre em nenhum dos casos.
Na MainActivity.kt, importei o navArgument e mudei a rota para "pedidos?cliente={cliente}". A sintaxe de query parameter é exatamente o que o Compose Navigation usa para distinguir parâmetros opcionais dos obrigatórios — diferente do {param} no meio da rota, que seria obrigatório. Dentro do navArgument("cliente"), defini defaultValue = "Cliente Genérico" para que a tela funcione normalmente mesmo quando nenhum valor for passado.
Na PedidosScreen.kt, o parâmetro cliente: String? entrou na assinatura como nullable, e o texto foi atualizado para "PEDIDOS - $cliente", exibindo tanto o valor padrão quanto qualquer valor que venha pela navegação.

Commit 4 — "Passagem de parâmetros obrigatórios na tela de Perfil"
Nesse commit mexi em 3 arquivos para fazer a tela de Perfil receber dados de forma dinâmica, em vez de simplesmente abrir com conteúdo fixo.
Na MainActivity.kt, atualizei a rota de "perfil" para "perfil/{nome}" — essa mudança foi necessária porque sem o parâmetro na rota, não há como o Compose Navigation saber que aquela tela precisa receber um valor externo. Para recuperar esse valor, usei it.arguments?.getString("nome", "Usuário Genérico"), que também garante um fallback caso nada seja enviado, e repassei o resultado direto para a PerfilScreen.
No MenuScreen.kt, a chamada navController.navigate("perfil") foi atualizada para navController.navigate("perfil/Fulano de Tal") — quando a rota tem um parâmetro obrigatório, ele precisa estar presente na navegação, senão a aplicação quebra.
Na PerfilScreen.kt, adicionei nome: String na assinatura da composable e atualizei o texto exibido para "PERFIL - $nome", fazendo a tela refletir o que chega pela navegação em vez de mostrar sempre o mesmo conteúdo.