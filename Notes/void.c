void merge_sort_1(int arr[]) { 
	int length = sizeof(arr) / sizeof(arr[0]); 
	if (length == 1)  
	return; 
	int middle = length / 2; 
	int* leftArray = malloc(middle * sizeof(int)); 
	int* rightArray = malloc(length – middle) * sizeof(int)); 
	if (leftArray == NULL || rightArray == NULL) { 
		printf(“Memory allocation failed \n”); 
		return 1;
	}
	int i = 0; 
	int j = 0; 
	for ( i = 0; i < length; i++) { 
		if ( i< middle) { 
			LeftArray[i] = arr[i];  
			} 
		else { 
			rightArray[j] = arr[i]; 
			j++; 
			} 
	merge_sort_1(leftArray); 
	merge_sort_1(rightArray); 
	merge_1(leftArray, rightArray, arr); 
	free(leftArray); 
	free(rightArray); 
} 