

#ifndef LINKEDLIST_C
#define LINKEDLIST_C

#include "linkedList.h"
#include <iostream>
using namespace std;
#include "linearStructure.h"




template <class T>
LinkedList<T>::LinkedList() {
    head=NULL;
}

template<class T>
LinkedList<T>::~LinkedList() {
    if (head!=NULL)
    {
        clear();
    }
}

template<class T>
LinkedList<T>::LinkedList(const LinkedList<T>& other)
{
    
    /* if (other.head == nullptr)
    {
        return;
    }
    
    Node<T> *curr = other.head;
    int i=0;
    
    while (curr->next != NULL)
    {
        T *el = new T(curr->element);
        insert(i, *el);
        i++;
        curr = curr->next;
    }
    insert(i, curr->element); */
    
    
    if(other.head == nullptr)
    {
        return;
    }
    Node<T> *temp = other.head;
    int i = 0;
    
    while(temp->next != NULL)
    {
        T *el = new T(temp->element);
        insert(i, *el);
        i++;
        temp = temp->next;
    }
    insert(i, temp->element);
    
    
}

template<class T>
void LinkedList<T>::insert(int index, T element)
{
    /*Node<T> *newNode = new Node<T>(element);
    Node<T> *curr = head;

    if (index>size()||index<0)
    {
        return;
    }else if (index==0)
    {
        if(head!=NULL)
        {
            newNode->next = head;
            head = newNode;
        }else{
            head = newNode;
        }
        return;
    }else if (index == size()-1)
    {
        
        while (curr->next!=NULL)
        {
            curr = curr->next;
        }
        curr->next = newNode;
    }else{
        
        Node<T> *previous;
        int pos = 0;
        while(pos != index)
        {
            previous = curr;
            curr = curr->next;
            pos++;
        }
        previous->next = newNode;
        newNode->next = curr;
        
    }*/
    
    if(index < 0 || index > size())
    {
        return;
    }else
    {
        int pos = 0;
        Node<T> *curr = head;
        Node<T> *node = new Node<T>(element);
        
        if(index == 0)
        {
            if(head == NULL)
            {
                head = node;
            }else
            {
                node->next = head;
                head = node;
            }
        }else if(index == size() - 1)
        {
            while(curr->next != NULL)
            {
                curr = curr->next;
            }
            curr->next = node;
            
        }else
        {
            Node<T> *prev;
            while(pos != index)
            {
                pos++;
                prev = curr;
                curr = curr->next;
            }
            prev->next = node;
            node->next = curr;
        }
        
        
    }
    
}


/*
 Removes and returns the element at the index passed in
 as a parameter.   If an invalid delete is attempted
 throw the string "empty structure".
 */
template<class T>
T LinkedList<T>::remove(int index)
{
    if (index==0&&head->next==NULL)
    {
        T el = head->element;
        head= NULL;
        return el;
    }
    if (index>size()||index<0)
    {
        throw "empty structure";
        
    }
    Node<T> *curr = head;
    for (int i=0; i<index-1;i++)
    {
        curr = curr->next;
    }
    T el = curr->element;
    curr->next = curr->next->next;
    return el;
}

/*
 Returns true if the list is empty, and false
 otherwise.
 */
template<class T>
bool LinkedList<T>::isEmpty()
{
    if (head==NULL)
    {
        return true;
    }else{
        return false;
    }
}

/*
 Removes all of the nodes from the list.  After this function has
 been called on a LinkedList object, the list must be empty.
 */
template<class T>
void LinkedList<T>::clear()
{

    Node<T> *curr = head;
    while(curr->next!=NULL)
    {
        remove(0);
    }
    remove(0);
}

template<class T>
LinkedList<T>& LinkedList<T>::clone()
{
    LinkedList<T> *newList = new LinkedList<T>;
    if (head!=nullptr)
    {
        LinkedList<T> *newList = new LinkedList<T>;
        Node<T> *curr = head;
        int count = 0;
        for(int i=0; i<size();i++)
        {
            T *el = new T(curr->element);
            newList->insert(i, *el);
            curr = curr->next;
            count++;
        }
        
        return *newList;
    }
  
    return *newList;
}




template<class T>
int LinkedList<T>::size()
{
    /*int count = 0;
    if (head==nullptr)
    {
        return 0;
    }else{
        
    Node<T> *curr = head;
    count++;
        if (curr->next==NULL)
        {
            return count;
        }
    while (curr->next!=NULL)
    {
        count++;
        curr = curr->next;
    }
        
    }
    return count; */
    
    int count = 0;
    
    if(head == NULL)
    {
        return count;
    }else
    {
        Node<T> *temp = head;
        count++;
        
        while(temp->next != NULL)
        {
            count++;
            temp = temp->next;
        }
        return count;
    }
    
    
}

/*
 Returns the head, not the element at the head.
 */

template<class T>
Node<T>* LinkedList<T>::getLeader()
{
    return head;
}



template<class T>
ostream& LinkedList<T>::print(ostream& os)
{
    Node<T> *curr = head;
    os<<"[";
    if (head==NULL)
    {
        os<<"]";
        return os;
    }else
    {
        
        while (curr->next!=NULL)
        {
            os<<curr->element<<",";
            curr = curr->next;
        }
        
        os<<curr->element<<"]";
    }
    return os;
}

template<class T>
LinkedList<T>& LinkedList<T>::operator=(const LinkedList<T>& other)
{
    if (other.head!=0)
    {
        clear();
        Node<T> *temp = other.head;
        int i=0;
        
        while (temp->next != NULL)
        {
            T *el = new T(temp->element);
            insert(i, *el);
            i++;
            temp = temp->next;
        }
        insert(i, temp->element);
        return *this;
    }
    return nullptr;
}

template<class T>
ostream& operator<<(ostream& os,LinkedList<T>& list)
{
    return list.print(os);
}

#endif
