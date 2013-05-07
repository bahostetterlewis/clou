# The Clojure Pitch


- From [Paul Grahm on Lisp](http://www.paulgraham.com/avg.html)
- All "high level" languages are not created equal

> You can see that machine language is very low level. But, at least as a kind of social convention, high-level languages are often all treated as equivalent. They're not. Technically the term "high-level language" doesn't mean anything very definite. There's no dividing line with machine languages on one side and all the high-level languages on the other. Languages fall along a continuum [4] of abstractness, from the most powerful all the way down to machine languages, which themselves vary in power. - Grahm

- just look at ruby...

- What about Python 2.x vs Python 3.x?
  + was there improvement?
  + this shows all high level languages (and versions of them) are by no means equivalent

- Don't get attached to any one language!


> The five languages that Eric Raymond recommends to hackers fall at various points on the power continuum. Where they fall relative to one another is a sensitive topic. What I will say is that I think Lisp is at the top. And to support this claim I'll tell you about one of the things I find missing when I look at the other four languages. How can you get anything done in them, I think, without macros?

---
> Many languages have something called a macro. But Lisp macros are unique. And believe it or not, what they do is related to the parentheses. The designers of Lisp didn't put all those parentheses in the language just to be different. To the Ruby, Python, Java, C/C++ programmer, Lisp code looks weird. But those parentheses are there for a reason. They are the outward evidence of a fundamental difference between Lisp and other languages. - Grahm


- Lisp code *is* data.
  + it consists of Lisp data objects
  + not data char and string data in a file but post parser, traversable, data structures.


### Clojure, the JVM, and Java oh my
- Clojure, being a relatively "new" language is a dialect of Lisp that was initially built on top of the JVM and now has implementations in
  - JavaScript
  - C
  - Lua
  - Python
  - and more
- Clojure is built Native to the JVM unlike ported languages like Jython and JRuby
  - this means it compiles to Java byte code and is designed for running efficiently on the JVM.


### Atomic Data Types
- Arbitrary precision integers - 1233455678
- Doubles 1.234, BigDecimals 1.234M
- Ratios 22/7
- Strings "fred", Charcacters \a \b \c
- Symbols - fred ethel, Keywords - :fred :ethel
- Booleans - true false, Null - nil
- Regex patterns #"a*b"

### Data Structures
- __Lists__ - singly linked, grow at front
  + (1 2 3 4 5), (fred ethel lucy), (list 1 2 3)
- __Vectors__ - indexed access, grow at end
  + [1 2 3 4 5]. [fred ethel lucy]
- __Maps__ - key/value associations
  + {:a 1 :b 2 :c 3}, {1 "fred", 2 "ethel"}
- __Sets__ #{fred ehtel lucy}
- __Everything nests__

### Syntax
- You've just seen it
- Data structures *are* the code
  + there are no other precedence rules or anything else
  + you write a Clojure program by writing the data structures, they are the code
  + this has many implications
  +*homoiconicity* - the representation of the program is done in the core data structures of the language



#### Java Interop
- So why go with Java? And I do I have know/use Java?
  + Java is a widely supported language on computer systems and the JVM is no joke
- Clojure has wrapper-free Java access

### Clojure is a lisp [[1]](http://www.youtube.com/watch?v=P76Vbsk_3J0)
*slide notes from video link above

- Dynamic
  + interactive environment
  + having a REPL
  + introspection capabilities on the env
  + ability to modify things in a running program
- Code as data
- Reader
  + thing between text and evaluator
- Small core
  + Clojure has way less syntax and complexities than languages like Python and Ruby
- Sequences
  + while traditionally Lisp languages put an emphasis on lists, Clojure differs in that it is more built around sequences (seqs)


### The REPL
- Read Eval Print Loop
- Define functions dynamically
- Load and compile code at runtime
- Introspection
  + look into environment namespaces and variables
  + interactive environment
- Compile time and runtime are not separate like in other languages
- Each function define in the REPL is compiled and added to the environment


### Clojure, and Lisp, make beautiful code
![cartoon](http://imgs.xkcd.com/comics/lisp_cycles.png)

    (defn remove-keys
      [mp ks]
      (apply dissoc mp ks))

    (def foo {:a "bar" :b "biz" :d "baz" :e "boz"})  ;; a hashmap of key value pairs

    (def keys-to-remove [:a :b :c])  ;; a vector of keys to remove

    => (remove-keys foo keys-to-remove)

    => {:d "baz", :e "boz"}


    (defmacro or
    "Evaluates exprs one at a time, from left to right. If a form
    returns a logical true value, or returns that value and doesn't
    evaluate any of the other expressions, otherwise it returns the
    value of the last expression. (or) returns nil."
    {:added "1.0"}
    ([] nil)
    ([x] x)
    ([x & next]
      `(let [or# ~x]
         (if or# or# (or ~@next)))))

#### Clojure is designed for parallelism and concurrency


#### Why A(nother) Lisp?
- Dynamic languages tend to be more concise. while some static languages like Haskell *can* be concise, the curly brace languages, like Java, are **not** concise.

- when debuging and coding in C++ and Java you spend a lot of time sorting out
  + which classes, objects, etc are touched by a given issue
  + and how far apart and spread out those pieces are
  + then how to fix/manage all of these mutable components from a function
- Functional programs in Clojure are fundamentally designed differently
  + most of the time there is a functional way to solve a problem, always try it first
  + avoid state at all costs, it just gets messy
  + keep functions pure of side effects
- Dynamic languages let you focus on the problem you care about. - [Rich Hickey - intro to Clojure pt1](http://www.youtube.com/watch?v=P76Vbsk_3J0)
![clojure](http://twimgs.com/ddj/images/article/2013/0313/clo1.gif)

### Rich Hickey is smart as shit, you should watch some of his talks
- [[1]](http://www.youtube.com/watch?v=P76Vbsk_3J0) [Rich Hickey - intro to Clojure pt1](http://www.youtube.com/watch?v=P76Vbsk_3J0)



---

#### Resources

- http://psg.com/~dlamkins/sl/chapter01.html
- http://www.paulgraham.com/avg.html
- http://landoflisp.com/
- http://wit.io/posts/clojure-all-grown-up
- http://www.drdobbs.com/architecture-and-design/the-clojure-philosophy/240150710

---