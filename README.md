# Funkcje ze Scali, ale rekurencyjnie
[Pokaż mi po prostu implementacje do skopiowania!](https://github.com/bartekmuzyk/bog_rekurencji/blob/master/src/main/scala/Main.scala)

**(pamiętaj żeby wywalić komentarze z dokumentacją tho, będą dziwnie wyglądały)**

## Przykłady wybranych funkcji
### `myFoldLeft(list, startAcc)(func)`

Funkcja `myFoldLeft` wykonuje następujące operacje w takiej kolejności:
- ustaw akumulator (nazwijmy go `acc`) na `startAcc`
- dla każdego elementu w `list`:
  - weź element z `list` (nazwijmy go `elem`)
  - wywołaj `func(value)` i zachowaj to co to wywołanie zwróciło (nazwijmy to co zwróciła ta funkcja `newAcc`)
  - ustaw `acc` na `newAcc`
  - powtórz
- kiedy skończą się elementy z `list`, zwróć cokolwiek jest w `acc`

Przykład:
```scala
myFoldLeft(
  List("abc", "ab", "a", "test"),
  0
)(
  (acc, elem) => acc + elem.length
)
```

Kroki dla przykładu:
- ustaw `acc` na `0`
- dla każdego elementu w `List("abc", "ab", "a", "test")`
  - dla elementu `"abc"`
    - weź `"abc"`
    - wywołaj `func("abc")`, co zwróci `acc + "abc".length`, czyli `0 + 3`, czyli `3`
    - ustaw `acc` na `3`
  - dla elementu `"ab"`
    - weź `"ab"`
    - wywołaj `func("ab")`, co zwróci `acc + "ab".length`, czyli `3 + 2`, czyli `5`
    - ustaw `acc` na `5`
  - dla elementu `"a"`
    - weź `"a"`
    - wywołaj `func("a")`, co zwróci `acc + "a".length`, czyli `5 + 1`, czyli `6`
    - ustaw `acc` na `6`
  - dla elementu `"test"`
    - weź `"test"`
    - wywołaj `func("test")`, co zwróci `acc + "test".length`, czyli `6 + 4`, czyli `10`
    - ustaw `acc` na `10`
- skończyły się elementy z `List("abc", "ab", "a", "test")`, więc zwracamy cokolwiek jest w `acc`, czyli `10`