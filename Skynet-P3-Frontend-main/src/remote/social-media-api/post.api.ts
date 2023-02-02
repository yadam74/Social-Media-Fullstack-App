import Post from '../../models/Post';

import Comment from '../../models/Comment';
import socialClient, { socialApiResponse } from './socialClient';

const baseURL = '/post';

const baseurl = '/comment';

export const apiGetPosts = async (): Promise<socialApiResponse> => {
  const response = await socialClient.get<any>(baseURL);
  return { status: response.status, payload: response.data };
};

export const apiGetAllPosts = async (): Promise<socialApiResponse> => {
  const response = await socialClient.get<any>(`${baseURL}/all`);
  return { status: response.status, payload: response.data };
};

export const apiGetComments = async (post: any): Promise<socialApiResponse> => {
  const response = await socialClient.post<any>(baseurl, post);
  return { status: response.status, payload: response.data };
};

export const apiUpsertPost = async (post: any): Promise<socialApiResponse> => {
  const response = await socialClient.put<any>(baseURL, post, {
    withCredentials: true,
  });
  return { status: response.status, payload: response.data };
};

export const apiUpsertComment = async (
  comment: any
): Promise<socialApiResponse> => {
  try {
    const response = await socialClient.put<any>(baseurl, comment, {
      withCredentials: true,
    });
    return { status: response.status, payload: response.data };
  } catch (e: any) {
    throw e;
  }
};

export const apiAddorRemoveLike = async (
  post: Post
): Promise<socialApiResponse> => {
  try {
    const response = await socialClient.put<any>(`${baseURL}/like`, post, {
      withCredentials: true,
    });
    return { status: response.status, payload: response.data };
  } catch (e: any) {
    throw e;
  }
};

export const apiDeletePost = async (post: Post): Promise<socialApiResponse> => {
  const response = await socialClient.delete<any>(`${baseURL}/delete-post`, {
    withCredentials: true,
    data: post,
  });
  apiGetPosts();
  return { status: response.status, payload: response.data };
};

export const apiDeleteComment = async (
  comment: Comment
): Promise<socialApiResponse> => {
  const response = await socialClient.delete<any>(`${baseurl}/delete-comment`, {
    withCredentials: true,
    data: comment,
  });
  apiGetComments(comment.post);
  return { status: response.status, payload: response.data };
};
