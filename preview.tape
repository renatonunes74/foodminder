Set Theme { "black": "#0a0f14", "red": "#c33027", "green": "#26a98b", "yellow": "#edb54b", "blue": "#195465", "purple": "#4e5165", "cyan": "#33859d", "white": "#a9b1d6", "brightBlack": "#414868", "brightRed": "#f7768e", "brightGreen": "#a6e3a1", "brightYellow": "#e0af68", "brightBlue": "#7aa2f7", "brightPurple": "#bb9af7", "brightCyan": "#7dcfff", "brightWhite": "#c0caf5", "background": "#0a0f14", "foreground": "#98d1ce", "selectionBackground": "#093748", "cursorColor": "#98d1ce" }
Output preview.mp4
Output preview.gif
Set FontSize 16
Set Framerate 60
Set Height 600
Set Padding 20
Set Shell zsh
Set TypingSpeed 0.2
Type "http POST localhost:8080/product name='Feijão' price_in_cents=861; : Postagem de produto" Sleep 2s Enter Sleep 2s
Type "http localhost:8080/product; : Lista de todos os produtos" Sleep 2s Enter Sleep 2s
Type "http PUT localhost:8080/product id=5d5e5e33-a8a6-4b3e-bb7b-185d52b8671b name='Milho de pipoca' price_in_cents=695; : Atualizando produto" Sleep 2s Enter Sleep 2s
Type "http DELETE localhost:8080/product/10638a63-a2f5-4052-96e4-2e8b1fe99985; : Deletanto produto" Sleep 2s Enter Sleep 2s
Type "http localhost:8080/product; : Alterações feitas" Sleep 2s Enter Sleep 7s
