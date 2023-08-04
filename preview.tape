Set Theme { "black": "#0a0f14", "red": "#c33027", "green": "#26a98b", "yellow": "#edb54b", "blue": "#195465", "purple": "#4e5165", "cyan": "#33859d", "white": "#a9b1d6", "brightBlack": "#414868", "brightRed": "#f7768e", "brightGreen": "#a6e3a1", "brightYellow": "#e0af68", "brightBlue": "#7aa2f7", "brightPurple": "#bb9af7", "brightCyan": "#7dcfff", "brightWhite": "#c0caf5", "background": "#0a0f14", "foreground": "#98d1ce", "selectionBackground": "#093748", "cursorColor": "#98d1ce" }
Output preview.gif
Set FontSize 16
Set Framerate 60
Set Height 600
Set Padding 20
Set Shell zsh
Set TypingSpeed 0.2

# POST 
Type "http POST :8080/planejamento data='2023-08-12' tipoRefeicao\[id\]=3 refeicao\[id\]=3; : Novo planejamento" Sleep 2s Enter Sleep 2s
Type "http POST :8080/refeicao nome='Lasanha' receita='receita.md'; : Nova refeição" Sleep 2s Enter Sleep 2s
Type "http POST :8080/tipoRefeicao tipo='Almoço'; : Novo tipo de refeição" Sleep 2s Enter Sleep 2s
# GET
Type "http localhost:8080/planejamento; : Lista de todos os planejamentos" Sleep 2s Enter Sleep 2s
Type "http localhost:8080/refeicao; : Lista de todas as refeições" Sleep 2s Enter Sleep 2s
Type "http localhost:8080/tipoRefeicao; : Lista de todos os tipos de refeições" Sleep 2s Enter Sleep 2s
# PUT
# DELETE

Type "; : Mais funcionalidades no README" Sleep 2s Enter Sleep 2s
