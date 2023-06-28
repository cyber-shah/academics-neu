import subprocess
import time
import sys
import math
from typing import Union

COMMON_ARG_FORMAT = "./Fibonacci.out {n} {algo} 0"
FORMAT = "markdown"
TIMEOUT = 30
COMMON_ARG_JAVA = "java Fibonacci {n} {algo} 0"
JAVA_SET = ('11', '12x', '13')
C_SET = ('0x', '1x', '2x')
LAST_RUN_TRACKER = {"0": 0.0, "1": 0.0, "2": 0.0, "11": 0.0, "12": 0.0, "13": 0.0}


def run_single(n: int, typ: Union[int, str], command=COMMON_ARG_FORMAT) -> float:
    command = command.format(n=n, algo=typ)
    if math.isnan(LAST_RUN_TRACKER.get(str(typ), math.nan)):
        return math.nan  # skip running if we are already timing out
    try:
        start = time.time()
        subprocess.run(command.split(), timeout=TIMEOUT)
        end = time.time()
        result = end - start
    except subprocess.TimeoutExpired:
        result = math.nan
    LAST_RUN_TRACKER[str(typ)] = result
    return result


def build_row(n: int) -> str:
    results_lst = []
    for t in C_SET:
        try:
            result = run_single(n, t)
        except KeyError:
            result = math.nan
        results_lst.append("-" if math.isnan(result) else f"{result:.5f}")
    iterative, recursive, dynamic_programming = results_lst

    results_lst = []
    for t in JAVA_SET:
        try:
            result = run_single(n, t, COMMON_ARG_JAVA)
        except KeyError:
            result = math.nan
        results_lst.append("-" if math.isnan(result) else f"{result:.5f}")
    iterative_p, recursive_p, dynamic_programming_p = results_lst

    if FORMAT == "markdown":
        return f"| {n:<4} | {iterative.center(8, ' ')} | {recursive.center(8, ' ')} | {dynamic_programming.center(8, ' ')} |" \
            + f"{iterative_p.center(8, ' ')} | {recursive_p.center(8, ' ')} | {dynamic_programming_p.center(8, ' ')} |"
    return f"{n},{iterative},{recursive},{dynamic_programming},{iterative_p},{recursive_p},{dynamic_programming_p}"


def table_header() -> str:
    if FORMAT == "markdown":
        return "| n | Iterative C | Recursive C | Dynamic Programming  C | Iterative P | Recursive P | Dynamic Programming  P |\n" + \
            "|--|:--:|:--:|:--:|:--:|:--:|:--:|"
    return "n,Iterative C,Recursive C,Dynamic Programming C,Iterative P,Recursive P,Dynamic Programming P"


def main(n):
    print(table_header())

    for i in range(1, n + 1):
        print(build_row(i*1000000))


if __name__ == "__main__":
    _n = 30 if len(sys.argv) < 2 else int(sys.argv[1])
    if len(sys.argv) == 3:
        FORMAT = "csv"
    main(_n)
