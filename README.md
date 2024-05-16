# Funkcje ze Scali, ale rekurencyjnie
[Pokaż mi po prostu implementacje do skopiowania!](https://github.com/bartekmuzyk/bog_rekurencji/blob/master/src/main/scala/Main.scala)

**(pamiętaj żeby wywalić komentarze z dokumentacją tho, będą dziwnie wyglądały)**

# Przykłady wybranych funkcji
## `myFoldLeft(list, startAcc)(func)`

Funkcja `myFoldLeft` wykonuje następujące operacje w takiej kolejności:
- ustaw akumulator (nazwijmy go `acc`) na `startAcc`
- dla każdego elementu w `list`:
  - weź element z `list` (nazwijmy go `elem`)
  - wywołaj `func(elem)` i zachowaj to co to wywołanie zwróciło (nazwijmy to co zwróciła ta funkcja `newAcc`)
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

## `myMap(list)(func)`
Funkcja `myMap` wykonuje następujące operacje w takiej kolejności:
- utwórz pustą listę roboczą `List()` (nazwijmy ją `result`)
- dla każdego elementu z `list`:
  - weź element `list` (nazwijmy go `elem`)
  - wywołaj `func(elem)` i zachowaj to co to wywołanie zwróciło (nazwijmy to co zwróciła ta funkcja `newElem`)
  - dodaj `newElem` na koniec listy `result`
- kiedy skończą się elementy z `list`, zwróć cokolwiek jest w `result`

Przykład:
```scala
myMap(
  List("foo", "test", "bar", "ab", "x")
)(
  elem => elem.length
)
```
Kroki dla przykładu:
- utwórz pustą listę roboczą `List()` (nazwijmy ją `result`)
- dla każdego elementu z `List("abc", "test", "foo", "bar", "ab", "x")`:
  - dla elementu `"foo"`
    - weź `"foo"`
    - wywołaj `func("foo")`, co zwróci `"foo".length`, czyli `3`
    - dodaj `3` na koniec listy `result` **(teraz: `result = List(3)`)**
  - dla elementu `"test"`
    - weź `"test"`
    - wywołaj `func("test")`, co zwróci `"test".length`, czyli `4`
    - dodaj `4` na koniec listy `result` **(teraz: `result = List(3, 4)`)**
  - dla elementu `"bar"`
    - weź `"bar"`
    - wywołaj `func("bar")`, co zwróci `"bar".length`, czyli `3`
    - dodaj `3` na koniec listy `result` **(teraz: `result = List(3, 4, 3)`)**
  - dla elementu `"ab"`
    - weź `"ab"`
    - wywołaj `func("ab")`, co zwróci `"ab".length`, czyli `2`
    - dodaj `2` na koniec listy `result` **(teraz: `result = List(3, 4, 3, 2)`)**
  - dla elementu `"x"`
    - weź `"x"`
    - wywołaj `func("x")`, co zwróci `"x".length`, czyli `1`
    - dodaj `1` na koniec listy `result` **(teraz: `result = List(3, 4, 3, 2, 1)`)**
- skończyły się elementy z `List("abc", "test", "foo", "bar", "ab", "x")`, więc zwracamy cokolwiek jest w `result`, czyli `List(3, 4, 3, 2, 1)`
  