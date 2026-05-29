# Graph Plotter

A desktop application for plotting mathematical functions built with Java Swing. 
Written as a personal project to explore GUI development and math expression parsing.

## Features

- Plot multiple equations simultaneously
- Supports standard math functions: `sin`, `cos`, `tan`, `log`, `sqrt`, and more
- Add and remove equation fields dynamically
- Smooth curve rendering with anti-aliasing

## Tech Stack

- Java Swing (GUI)
- [mXparser](https://mathparser.org/) (expression evaluation)

## Getting Started

### Prerequisites

- Java 11 or higher
- mXparser library added to your classpath

### Running the Project

1. Clone the repository
2. Add the mXparser `.jar` to your project dependencies
3. Run `Main.java`

## Usage

1. Type a function in the `f(x) =` field, e.g. `sin(x)`, `x^2`, `log(x)`
2. Click `+ Add Equation` to plot multiple functions at once
3. Click `Plot Graphs` to render

## Project Structure

src/
├── Main.java
├── GUI/
│   ├── Frame.java
│   └── Plotter.java
└── Engine/
└── EquationHandler.java0

## Limitation

- X range is fixed as of right now
- No zoom or pan support
- Some functions may render as discontinued due to the zoom or pan
