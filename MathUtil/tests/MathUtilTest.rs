
// Import the Rust testing module
#[cfg(test)]
mod tests {
    mod super::math;
    use math::MathUtil::FunctionCollection;
    
    // Test case 1: Test with n = 0
    #[test]
    fn test_sum_up_to_n_with_zero() {
        let result = sum_up_to_n(0);
        assert_eq!(result, 0);
    }

    // Test case 2: Test with n = 1
    #[test]
    fn test_sum_up_to_n_with_one() {
        let result = sum_up_to_n(1);
        assert_eq!(result, 1);
    }

    // Test case 3: Test with n = 5
    #[test]
    fn test_sum_up_to_n_with_five() {
        let result = sum_up_to_n(5);
        assert_eq!(result, 15);
    }

    // You can add more test cases as needed
}