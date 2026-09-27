import React, { useState, useEffect } from 'react';
import { useLanguage } from '../context/LanguageContext';
import { useAuth } from '../context/AuthContext';
import {
  Users,
  MessageSquare,
  Send,
  Plus,
  Filter,
  MapPin,
  Clock,
  Sparkles,
  ChevronDown,
  ChevronUp,
  X,
  Share2,
  Tag,
  Languages,
  CheckCircle2
} from 'lucide-react';
import { ALL_INDIAN_DISTRICTS } from '../data/allDistricts';

const CROP_TRANSLATIONS = {
  'Onion': 'प्याज',
  'Chilli': 'मिर्च',
  'Wheat': 'गेहूं',
  'Irrigation': 'सिंचाई',
  'Tomato': 'टमाटर',
  'General': 'सामान्य',
  'Rice': 'धान',
  'Cotton': 'कपास',
  'Potato': 'आलू',
  'Corn': 'मक्का',
  'Sugarcane': 'गन्ना',
  'Grape': 'अंगूर',
  'Mustard': 'सरसों',
  'Soybean': 'सोयाबीन',
  'Paddy': 'धान',
  'Garlic': 'लहसुन',
  'Ginger': 'अदरक'
};

export default function Saathi() {
  const { t, language } = useLanguage();
  const { user } = useAuth();

  const [posts, setPosts] = useState([]);
  const [selectedDistrict, setSelectedDistrict] = useState('All Districts');
  const [loading, setLoading] = useState(true);
  const [expandedComments, setExpandedComments] = useState({});
  const [commentInputs, setCommentInputs] = useState({});
  const [isModalOpen, setIsModalOpen] = useState(false);

  // Per-card translation override toggle (e.g. { [postId]: 'hi' | 'en' })
  const [cardLangOverrides, setCardLangOverrides] = useState({});

  // New post form state
  const [newTitle, setNewTitle] = useState('');
  const [newDesc, setNewDesc] = useState('');
  const [newDistrict, setNewDistrict] = useState('Nashik');
  const [newCropTag, setNewCropTag] = useState('');
  const [submitting, setSubmitting] = useState(false);

  // Auto-populate district from logged-in user profile if available
  useEffect(() => {
    if (user?.district) {
      setNewDistrict(user.district);
    }
  }, [user]);

  const fetchPosts = () => {
    setLoading(true);
    const url = selectedDistrict === 'All Districts'
      ? '/api/posts'
      : `/api/posts?district=${encodeURIComponent(selectedDistrict)}`;

    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        setPosts(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error('Failed to fetch posts:', err);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchPosts();
  }, [selectedDistrict]);

  const toggleComments = (postId) => {
    setExpandedComments((prev) => ({
      ...prev,
      [postId]: !prev[postId]
    }));
  };

  const toggleCardLanguage = (postId, currentEffectiveLang) => {
    setCardLangOverrides((prev) => ({
      ...prev,
      [postId]: currentEffectiveLang === 'hi' ? 'en' : 'hi'
    }));
  };

  const getDistrictState = (districtName) => {
    const found = ALL_INDIAN_DISTRICTS.find(
      (d) => d.name.toLowerCase() === districtName.toLowerCase()
    );
    return found ? found.state : (user?.state || 'Maharashtra');
  };

  const formatAuthorName = (authorName) => {
    if (!authorName) {
      return language === 'hi' ? 'किसान मित्र' : 'Farmer Member';
    }
    // Check if authorName corresponds to the currently logged in user
    if (user?.fullName) {
      const cleanPostAuthor = authorName.replace(/\s*\((You|आप)\)\s*/gi, '').trim().toLowerCase();
      const cleanUser = user.fullName.trim().toLowerCase();
      if (cleanPostAuthor === cleanUser || authorName.toLowerCase().includes(cleanUser)) {
        return `${user.fullName} (${language === 'hi' ? 'आप' : 'You'})`;
      }
    }
    return authorName;
  };

  const handleAddComment = async (postId) => {
    const text = commentInputs[postId];
    if (!text || !text.trim()) return;

    // Use logged in user's full name, or fallback to friendly role
    const authorName = user?.fullName?.trim() || (language === 'hi' ? 'किसान मित्र' : 'Farmer Member');

    try {
      const res = await fetch(`/api/posts/${postId}/comments`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          text: text.trim(),
          authorName: authorName
        })
      });

      if (res.ok) {
        const savedComment = await res.json();
        // Optimistically update post comments in UI
        setPosts((prevPosts) =>
          prevPosts.map((p) => {
            if (p.id === postId) {
              const currentComments = p.comments || [];
              return { ...p, comments: [...currentComments, savedComment] };
            }
            return p;
          })
        );
        // Clear input
        setCommentInputs((prev) => ({ ...prev, [postId]: '' }));
      }
    } catch (err) {
      console.error('Failed to add comment:', err);
    }
  };

  const handleCreatePost = async (e) => {
    e.preventDefault();
    if (!newTitle.trim() || !newDesc.trim()) return;

    setSubmitting(true);
    const authorName = user?.fullName?.trim() || (language === 'hi' ? 'किसान मित्र' : 'Farmer Member');
    const stateName = getDistrictState(newDistrict);

    try {
      const res = await fetch('/api/posts', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          title: newTitle.trim(),
          description: newDesc.trim(),
          district: newDistrict,
          state: stateName,
          authorName: authorName,
          cropTag: newCropTag.trim() || (language === 'hi' ? 'सामान्य' : 'General')
        })
      });

      if (res.ok) {
        const created = await res.json();
        setPosts([created, ...posts]);
        setIsModalOpen(false);
        setNewTitle('');
        setNewDesc('');
        setNewCropTag('');
      }
    } catch (err) {
      console.error('Failed to create post:', err);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="space-y-6 max-w-6xl mx-auto animate-fadeIn">
      {/* Top Banner */}
      <div className="bg-white rounded-3xl p-6 sm:p-8 border border-[#E2D9CC] shadow-sm relative overflow-hidden">
        <div className="absolute inset-0 furrow-pattern pointer-events-none opacity-40" />
        <div className="relative flex flex-col md:flex-row md:items-center md:justify-between gap-4">
          <div>
            <div className="inline-flex items-center space-x-1.5 px-3 py-1 rounded-full text-xs font-semibold bg-[#EAF3E7] text-[#4C7A3D] border border-[#D1E6CC] mb-2">
              <Users size={14} />
              <span>{language === 'hi' ? 'किसान चौपाल व ज्ञान साझा' : 'Peer-to-Peer Farmer Community'}</span>
            </div>
            <h1 className="text-2xl sm:text-3xl font-bold text-[#6B4423] font-['Poppins']">
              {t('saathiTitle')}
            </h1>
            <p className="text-xs sm:text-sm text-[#5C4533] mt-1">
              {t('saathiSubtitle')}
            </p>
            {user && (
              <div className="mt-2.5 inline-flex items-center space-x-1.5 text-xs text-[#4C7A3D] font-medium bg-[#EAF3E7]/80 px-2.5 py-1 rounded-lg border border-[#D1E6CC]">
                <CheckCircle2 size={13} />
                <span>
                  {language === 'hi'
                    ? `सक्रिय सदस्य: ${user.fullName} (${user.district || 'भारत'})`
                    : `Active member: ${user.fullName} (${user.district || 'India'})`}
                </span>
              </div>
            )}
          </div>

          {/* New Post Button */}
          <button
            type="button"
            onClick={() => setIsModalOpen(true)}
            className="self-start md:self-auto inline-flex items-center space-x-2 px-5 py-3 rounded-2xl bg-[#4C7A3D] hover:bg-[#3F6632] text-white font-bold text-sm shadow-md transition-all transform active:scale-95"
          >
            <Plus size={18} />
            <span>{t('saathiNewPostBtn')}</span>
          </button>
        </div>

        {/* District Filter Bar */}
        <div className="mt-6 pt-4 border-t border-[#EFE8DC] flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3">
          <div className="flex items-center space-x-2">
            <Filter size={16} className="text-[#6B4423]" />
            <span className="text-xs font-bold uppercase text-[#8A7463] tracking-wide">
              {t('saathiFilterDistrict')}:
            </span>
          </div>

          <div className="flex flex-wrap items-center gap-2">
            <select
              value={selectedDistrict}
              onChange={(e) => setSelectedDistrict(e.target.value)}
              className="bg-[#F7F2E9] px-3.5 py-1.5 rounded-xl border border-[#DECDBE] text-xs font-bold text-[#6B4423] focus:outline-none focus:border-[#4C7A3D] cursor-pointer max-w-[260px]"
            >
              <option value="All Districts">{t('saathiAllDistricts')} ({ALL_INDIAN_DISTRICTS.length} Districts A-Z)</option>
              {ALL_INDIAN_DISTRICTS.map((dist) => (
                <option key={`${dist.name}-${dist.state}`} value={dist.name}>
                  {dist.name} ({dist.state})
                </option>
              ))}
            </select>

            {selectedDistrict !== 'All Districts' && (
              <button
                type="button"
                onClick={() => setSelectedDistrict('All Districts')}
                className="text-xs px-2.5 py-1 rounded-lg bg-white border border-[#DECDBE] text-[#C46A2B] hover:text-[#A8531D] font-bold transition-colors"
              >
                ✕ {language === 'hi' ? 'सभी जिले देखें' : 'Show All'}
              </button>
            )}
          </div>
        </div>
      </div>

      {/* Loading Skeleton */}
      {loading && (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {[1, 2, 3, 4].map((i) => (
            <div key={i} className="bg-white rounded-3xl p-6 border border-[#E2D9CC] h-64 animate-pulse" />
          ))}
        </div>
      )}

      {/* Posts Feed Grid */}
      {!loading && posts.length > 0 && (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {posts.map((post) => {
            const isExpanded = !!expandedComments[post.id];
            const commentsCount = post.comments ? post.comments.length : 0;

            // Determine effective language for this post card
            const effectiveLang = cardLangOverrides[post.id] || language;
            const isHi = effectiveLang === 'hi';

            // Bilingual title, description, crop tag
            const displayTitle = isHi
              ? (post.titleHi && post.titleHi.trim() ? post.titleHi : post.title)
              : (post.title && post.title.trim() ? post.title : post.titleHi);

            const displayDesc = isHi
              ? (post.descriptionHi && post.descriptionHi.trim() ? post.descriptionHi : post.description)
              : (post.description && post.description.trim() ? post.description : post.descriptionHi);

            const displayCropTag = isHi
              ? (CROP_TRANSLATIONS[post.cropTag] || post.cropTag)
              : post.cropTag;

            const authorFormatted = formatAuthorName(post.authorName);

            return (
              <article
                key={post.id}
                className="bg-white rounded-3xl p-6 border border-[#E2D9CC] shadow-sm flex flex-col justify-between hover:shadow-md transition-all duration-300 relative"
              >
                <div>
                  {/* Post Author, Location & Crop Header */}
                  <div className="flex items-center justify-between pb-3 border-b border-[#EFE8DC]">
                    <div className="flex items-center space-x-2.5">
                      <div className="w-10 h-10 rounded-2xl bg-[#EAF3E7] text-[#4C7A3D] font-bold flex items-center justify-center text-sm shadow-inner">
                        {authorFormatted ? authorFormatted.charAt(0) : '👨‍🌾'}
                      </div>
                      <div>
                        <h2 className="text-xs font-bold text-[#2C1E14]">
                          {authorFormatted}
                        </h2>
                        <span className="text-[11px] text-[#8A7463] flex items-center gap-1">
                          <MapPin size={11} className="text-[#C46A2B]" />
                          {post.district}, {post.state}
                        </span>
                      </div>
                    </div>

                    <div className="flex items-center space-x-1.5">
                      {post.cropTag && (
                        <span className="text-[10px] uppercase font-bold tracking-wider px-2 py-0.5 rounded-full bg-[#FDF2E9] text-[#C46A2B] border border-[#F6DCC7]">
                          {displayCropTag}
                        </span>
                      )}
                    </div>
                  </div>

                  {/* Post Title & Description */}
                  <div className="mt-4 space-y-2">
                    <h2 className="text-base font-bold text-[#6B4423] font-['Poppins'] leading-snug">
                      {displayTitle}
                    </h2>
                    <p className="text-xs sm:text-sm text-[#5C4533] leading-relaxed whitespace-pre-line">
                      {displayDesc}
                    </p>
                  </div>

                  {/* Card Inline Translation Switcher */}
                  <div className="mt-3">
                    <button
                      type="button"
                      onClick={() => toggleCardLanguage(post.id, effectiveLang)}
                      className="inline-flex items-center space-x-1 text-[11px] px-2.5 py-1 rounded-lg bg-[#F7F2E9] border border-[#DECDBE] text-[#6B4423] hover:text-[#4C7A3D] hover:border-[#4C7A3D] font-semibold transition-colors"
                      title={isHi ? 'Translate back to English' : 'Translate post to Hindi'}
                    >
                      <Languages size={12} className="text-[#4C7A3D]" />
                      <span>{isHi ? 'View English text' : 'हिंदी अनुवाद देखें'}</span>
                    </button>
                  </div>
                </div>

                {/* Post Footer & Comments Section */}
                <div className="mt-6 pt-3 border-t border-[#EFE8DC]">
                  <div className="flex items-center justify-between">
                    <button
                      type="button"
                      onClick={() => toggleComments(post.id)}
                      className="inline-flex items-center space-x-1.5 text-xs font-bold text-[#4C7A3D] hover:text-[#3F6632] transition-colors py-1"
                    >
                      <MessageSquare size={15} />
                      <span>
                        {commentsCount} {t('saathiComments')}
                      </span>
                      {isExpanded ? <ChevronUp size={14} /> : <ChevronDown size={14} />}
                    </button>

                    <span className="text-[10px] text-[#8A7463]">
                      {new Date(post.createdAt).toLocaleDateString()}
                    </span>
                  </div>

                  {/* Expandable Comments List */}
                  {isExpanded && (
                    <div className="mt-3 pt-3 border-t border-[#DECDBE]/60 space-y-3 animate-fadeIn">
                      <div className="space-y-2 max-h-56 overflow-y-auto pr-1">
                        {post.comments && post.comments.length > 0 ? (
                          post.comments.map((comment, cIdx) => {
                            const commentAuthor = formatAuthorName(comment.authorName);
                            const commentText = isHi
                              ? (comment.textHi && comment.textHi.trim() ? comment.textHi : comment.text)
                              : (comment.text && comment.text.trim() ? comment.text : comment.textHi);

                            return (
                              <div
                                key={comment.id || cIdx}
                                className="p-2.5 rounded-xl bg-[#F7F2E9] border border-[#DECDBE] text-xs space-y-1"
                              >
                                <div className="flex items-center justify-between">
                                  <span className="font-bold text-[#6B4423]">
                                    {commentAuthor}
                                  </span>
                                  <span className="text-[10px] text-[#8A7463]">
                                    {comment.createdAt
                                      ? new Date(comment.createdAt).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })
                                      : ''}
                                  </span>
                                </div>
                                <p className="text-[#3D2612] leading-relaxed">
                                  {commentText}
                                </p>
                              </div>
                            );
                          })
                        ) : (
                          <p className="text-[11px] text-[#8A7463] text-center py-2">
                            {language === 'hi' ? 'अभी कोई टिप्पणी नहीं है। सबसे पहले जवाब दें!' : 'No replies yet. Be the first to advise!'}
                          </p>
                        )}
                      </div>

                      {/* Add Comment Input Bar */}
                      <div className="flex items-center space-x-1.5 pt-1">
                        <input
                          type="text"
                          value={commentInputs[post.id] || ''}
                          onChange={(e) =>
                            setCommentInputs({ ...commentInputs, [post.id]: e.target.value })
                          }
                          onKeyDown={(e) => {
                            if (e.key === 'Enter') handleAddComment(post.id);
                          }}
                          placeholder={t('saathiAddCommentPlaceholder')}
                          className="flex-1 px-3 py-2 text-xs rounded-xl border border-[#DECDBE] bg-white text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
                        />
                        <button
                          type="button"
                          onClick={() => handleAddComment(post.id)}
                          className="p-2 rounded-xl bg-[#4C7A3D] text-white hover:bg-[#3F6632] transition-colors"
                        >
                          <Send size={14} />
                        </button>
                      </div>
                    </div>
                  )}
                </div>
              </article>
            );
          })}
        </div>
      )}

      {/* Empty State */}
      {!loading && posts.length === 0 && (
        <div className="bg-white rounded-3xl p-12 text-center border border-[#E2D9CC] space-y-3">
          <div className="w-16 h-16 mx-auto rounded-3xl bg-[#F7F2E9] text-[#6B4423] flex items-center justify-center text-2xl">
            💬
          </div>
          <h2 className="text-lg font-bold text-[#6B4423]">
            {t('saathiNoPosts')}
          </h2>
          <button
            type="button"
            onClick={() => setIsModalOpen(true)}
            className="px-5 py-2.5 rounded-2xl bg-[#4C7A3D] text-white font-bold text-xs shadow-md"
          >
            {t('saathiNewPostBtn')}
          </button>
        </div>
      )}

      {/* New Post Modal Form */}
      {isModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-[#2C1E14]/70 backdrop-blur-sm p-4 animate-fadeIn">
          <div className="bg-[#F7F2E9] border border-[#DECDBE] rounded-3xl max-w-lg w-full p-6 sm:p-8 shadow-2xl relative">
            <div className="flex items-center justify-between pb-3 border-b border-[#DECDBE]">
              <div className="flex items-center space-x-2">
                <div className="w-8 h-8 rounded-xl bg-[#4C7A3D] text-white flex items-center justify-center">
                  <Plus size={18} />
                </div>
                <div>
                  <h2 className="text-lg font-bold text-[#6B4423] font-['Poppins']">
                    {t('saathiNewPostBtn')}
                  </h2>
                  <p className="text-[11px] text-[#8A7463]">
                    {language === 'hi' ? 'लेखक नाम' : 'Posting as'}: <span className="font-bold text-[#4C7A3D]">{user?.fullName || (language === 'hi' ? 'किसान मित्र' : 'Farmer Member')}</span>
                  </p>
                </div>
              </div>
              <button
                type="button"
                onClick={() => setIsModalOpen(false)}
                className="p-1.5 rounded-lg text-[#8A7463] hover:text-[#2C1E14]"
              >
                <X size={18} />
              </button>
            </div>

            <form onSubmit={handleCreatePost} className="mt-4 space-y-4">
              <div>
                <label className="block text-xs font-bold text-[#6B4423] uppercase mb-1">
                  {language === 'hi' ? 'शीर्षक / समस्या' : 'Title / Topic'}
                </label>
                <input
                  type="text"
                  required
                  value={newTitle}
                  onChange={(e) => setNewTitle(e.target.value)}
                  placeholder={t('saathiPostTitlePlaceholder')}
                  className="w-full px-3.5 py-2.5 rounded-xl border border-[#DECDBE] bg-white text-sm text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-[#6B4423] uppercase mb-1">
                  {language === 'hi' ? 'विवरण व अनुभव' : 'Description & Questions'}
                </label>
                <textarea
                  rows={4}
                  required
                  value={newDesc}
                  onChange={(e) => setNewDesc(e.target.value)}
                  placeholder={t('saathiPostDescPlaceholder')}
                  className="w-full px-3.5 py-2.5 rounded-xl border border-[#DECDBE] bg-white text-sm text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
                />
              </div>

              <div className="grid grid-cols-2 gap-3">
                <div>
                  <label className="block text-xs font-bold text-[#6B4423] uppercase mb-1">
                    {language === 'hi' ? 'जिला' : 'District'}
                  </label>
                  <select
                    value={newDistrict}
                    onChange={(e) => setNewDistrict(e.target.value)}
                    className="w-full px-3 py-2 rounded-xl border border-[#DECDBE] bg-white text-sm text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
                  >
                    {ALL_INDIAN_DISTRICTS.map((d) => (
                      <option key={`${d.name}-${d.state}`} value={d.name}>
                        {d.name}, {d.state}
                      </option>
                    ))}
                  </select>
                </div>

                <div>
                  <label className="block text-xs font-bold text-[#6B4423] uppercase mb-1">
                    {language === 'hi' ? 'फसल टैग' : 'Crop Tag'}
                  </label>
                  <input
                    type="text"
                    value={newCropTag}
                    onChange={(e) => setNewCropTag(e.target.value)}
                    placeholder="e.g. Onion, Wheat, Tomato"
                    className="w-full px-3 py-2 rounded-xl border border-[#DECDBE] bg-white text-sm text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
                  />
                </div>
              </div>

              <div className="pt-2 flex items-center justify-end space-x-2">
                <button
                  type="button"
                  onClick={() => setIsModalOpen(false)}
                  className="px-4 py-2.5 rounded-xl bg-[#EFE8DC] text-[#6B4423] text-xs font-bold hover:bg-[#DECDBE]"
                >
                  {language === 'hi' ? 'रद्द करें' : 'Cancel'}
                </button>
                <button
                  type="submit"
                  disabled={submitting}
                  className="px-6 py-2.5 rounded-xl bg-[#4C7A3D] hover:bg-[#3F6632] disabled:opacity-50 text-white text-xs font-bold shadow-md transition-all"
                >
                  {submitting ? t('loading') : t('saathiPublishBtn')}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
