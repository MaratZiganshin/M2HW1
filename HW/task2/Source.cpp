#include <iostream>
#include <ctime>
#include <chrono>

long* generateNumbers(int N)
{
    long* result = new long[N];
    for (int i = 0; i < N; i++)
    {
        result[i] = RAND_MAX*rand() + rand();
    }
    return result;
}

void sort(long* array, int start, int end)
{
    if (start >= end)
        return;
    int i = start, j = end;
    int cur = i - (i - j) / 2;
    while (i < j)
    {
        while (i < cur && (array[i] <= array[cur]))
        {
            i++;
        }
        while (j > cur && (array[cur] <= array[j]))
        {
            j--;
        }
        if (i < j)
        {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            if (i == cur)
                cur = j;
            else if (j == cur)
                cur = i;
        }
    }
    sort(array, start, cur);
    sort(array, cur + 1, end);
}

long* merge(long* first, long* second, int firstSize, int secondSize)
{
    int indexFirst = 0;
    int indexSecond = 0;
    int indexResult = 0;
    long* result = new long[firstSize + secondSize];

    while (indexFirst < firstSize && indexSecond < secondSize)
    {
        if (first[indexFirst] < second[indexSecond])
        {
            result[indexResult] = first[indexFirst];
            indexFirst++;
        }
        else
        {
            result[indexResult] = second[indexSecond];
            indexSecond++;
        }
        indexResult++;
    }

    while (indexFirst < firstSize)
    {
        result[indexResult] = first[indexFirst];
        indexFirst++;
        indexResult++;
    }

    while (indexSecond < secondSize)
    {
        result[indexResult] = second[indexSecond];
        indexSecond++;
        indexResult++;
    }
    return result;
}

int main()
{
    srand(time(0));
    std::cout << "Size of first array" << std::endl;
    int n1;
    std::cin >> n1;
    std::cout << "Size of second array" << std::endl;
    int n2;
    std::cin >> n2;
    long* first = generateNumbers(n1);
    long* second = generateNumbers(n2);

    long* warmArray1;
    long* warmArray2;

    for (int i = 0; i < 100; i++)
    {
        warmArray1 = generateNumbers(100000);
        sort(warmArray1, 0, 99999);
        warmArray2 = generateNumbers(100000);
        sort(warmArray2, 0, 99999);
    }

    auto currentTime = std::chrono::high_resolution_clock::now();
    sort(first, 0, n1);
    auto elapsed = std::chrono::high_resolution_clock::now();
    std::cout << "First array sort: " << std::chrono::duration_cast<std::chrono::nanoseconds>(elapsed - currentTime).count() << "ns" << std::endl;

    currentTime = std::chrono::high_resolution_clock::now();
    sort(second, 0, n2 - 1);
    elapsed = std::chrono::high_resolution_clock::now();
    std::cout << "Second array sort: " << std::chrono::duration_cast<std::chrono::nanoseconds>(elapsed - currentTime).count() << "ns" << std::endl;

    for (int i = 0; i < 100; i++)
    {
        long* warmArray3 = merge(first, second, n1, n2);
    }

    currentTime = std::chrono::high_resolution_clock::now();
    long* result = merge(first, second, n1, n2);
    elapsed = std::chrono::high_resolution_clock::now();
    std::cout << "Arrays merge: " << std::chrono::duration_cast<std::chrono::nanoseconds>(elapsed - currentTime).count() << "ns" << std::endl;

    for (int i = 0; i < n1 - 1; i++)
    {
        if (first[i] > first[i + 1])
        {
            std::cout << ("error");
            break;
        }
    }
    for (int i = 0; i < n2 - 1; i++)
    {
        if (second[i] > second[i + 1])
        {
            std::cout << ("error");
            break;
        }
    }
    for (int i = 0; i < n1 + n2 - 1; i++)
    {
        if (result[i] > result[i + 1])
        {
            std::cout << ("error");
            break;
        }
    }
    system("pause");
}