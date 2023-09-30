pub struct FunctionCollection;

impl FunctionCollection {

// Function 1: Sum of all numbers from 1 to n using a for loop
pub fn sum_up_to_n(n: i32) -> i32 {
    let mut sum = 0;
    for i in 1..=n {
        sum += i;
    }
    sum
}

// Function 2: Check if a number is even using an if-else statement
pub fn is_even(num: i32) -> bool {
    if num % 2 == 0 {
        true
    } else {
        false
    }
}

// Function 3: Calculate the factorial of a number using a for loop
pub fn factorial(n: u32) -> u32 {
    let mut result = 1;
    for i in 1..=n {
        result *= i;
    }
    result
}

// Function 4: Find the maximum number in a vector using a for loop and if-else
pub fn find_max(numbers: &[i32]) -> i32 {
    let mut max = i32::MIN;
    for &num in numbers {
        if num > max {
            max = num;
        }
    }
    max
}

// Function 5: Check if a string is empty using an if-else statement
pub fn is_empty_string(s: &str) -> bool {
    if s.is_empty() {
        true
    } else {
        false
    }
}

// Function 6: Count the number of even numbers in a vector using a for loop and if-else
pub fn count_even_numbers(numbers: &[i32]) -> usize {
    let mut count = 0;
    for &num in numbers {
        if num % 2 == 0 {
            count += 1;
        }
    }
    count
}

// Function 7: Check if a number is prime using a for loop and if-else
pub fn is_prime(num: u32) -> bool {
    if num <= 1 {
        return false;
    }
    for i in 2..(num / 2 + 1) {
        if num % i == 0 {
            return false;
        }
    }
    true
}

// Function 8: Calculate the Fibonacci sequence using a for loop
pub fn fibonacci(n: usize) -> Vec<u64> {
    let mut sequence = vec![0, 1];
    for i in 2..n {
        let next = sequence[i - 1] + sequence[i - 2];
        sequence.push(next);
    }
    sequence
}

// Function 9: Check if a vector contains duplicates using a for loop and if-else
pub fn has_duplicates(numbers: &[i32]) -> bool {
    for (i, &num1) in numbers.iter().enumerate() {
        for (j, &num2) in numbers.iter().enumerate() {
            if i != j && num1 == num2 {
                return true;
            }
        }
    }
    false
}

// Function 10: Find the minimum number in a vector using a for loop and if-else
pub fn find_min(numbers: &[i32]) -> i32 {
    let mut min = i32::MAX;
    for &num in numbers {
        if num < min {
            min = num;
        }
    }
    min
}

// Function 11: Calculate the average of numbers in a vector using a for loop
pub fn calculate_average(numbers: &[f64]) -> f64 {
    let mut sum = 0.0;
    for &num in numbers {
        sum += num;
    }
    sum / (numbers.len() as f64)
}

// Function 12: Check if a string contains a specific substring using an if-else statement
pub fn contains_substring(s: &str, substring: &str) -> bool {
    if s.contains(substring) {
        true
    } else {
        false
    }
}

}


#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_sum_up_to_n_with_input_0() {
        let result = FunctionCollection::sum_up_to_n(0);
        assert_eq!(result, 0, "F1-TID1 Failed: Expected the result to be 0, but got {}", result);
    }
    #[test]
    fn test_sum_up_to_n_with_usual_input() {
        let result = FunctionCollection::sum_up_to_n(2);
        assert_eq!(result, 3, "F1-TID2 Failed: Expected the result to be 3, but got {}", result);
    }

    #[test]
    fn test_is_even_with_even_number() {
        let result = FunctionCollection::is_even(4);
        assert!(result, "F2-TID1 Failed: Expected the result to be true, but got {}", result);
    }

    #[test]
    fn test_is_even_with_odd_number() {
        let result = FunctionCollection::is_even(3);
        assert!(!result, "F2-TID2 Failed: Expected the result to be false, but got {}", result);
    }

    #[test]
    fn test_factorial_for_zero() {
        let result = FunctionCollection::factorial(0);
        assert_eq!(result, 1, "F3-TID1 Failed: Expected the result to be 1, but got {}", result);
    }

    #[test]
    fn test_factorial_for_usual_input() {
        let result = FunctionCollection::factorial(2);
        assert_eq!(result, 2, "F3-TID2 Failed: Expected the result to be 2, but got {}", result);
    }
}
