# Eta Higher-Kinded Types (HKTS)

## Running

```shell
./run.sh
```

## Description

 The Eta runtime currently does not expose a generics-based interface to run pure Eta functions and computations from the Java side, so working with Eta data types on the
 Java side has absolutely no type-safety.

This repository is an experiment to try out a way to use Eta functions & typeclasses
from the Java side without having to do manual `foreign export`'s and without 
(significant) loss of type-safety.

The class hierarchy closely matches the existing hierarchy in the Eta runtime, but has
absolutely no support for advanced runtime features (i.e concurrency, off-heap memory, etc). It is an extremely simple implementation of graph reduction via STG and makes
no attempt at optimization.

Features:
- Extensive use of F-bounded polymorphism.
- Relatively simple types on the Java side.

This is an ongoing investigation and once it is determined that we can get decent
type-safety on the Java side for a wide range of features, the main Eta compiler
and runtime system will be modified to expose generics-based API for using Eta 
libraries directly from Java.

## Source Code Navigation
The entry point of the code is `src/HKT.java`. The remaining files setup the class
hierarchy that would be used for a lazily-evaluated, pure functional language.

## Prerequisites
- Java 1.5+ (Generics support)
- Unix-based OS
  - If you don't have a Unix-based OS (i.e. Windows), you can try manually running
    the commands listed in the `run.sh` script in the Command Prompt.

